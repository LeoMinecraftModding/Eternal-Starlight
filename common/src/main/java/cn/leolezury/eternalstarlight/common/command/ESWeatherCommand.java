package cn.leolezury.eternalstarlight.common.command;

import cn.leolezury.eternalstarlight.common.data.DimensionInit;
import cn.leolezury.eternalstarlight.common.init.WeatherInit;
import cn.leolezury.eternalstarlight.common.util.WeatherUtil;
import cn.leolezury.eternalstarlight.common.weather.AbstractWeather;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.arguments.TimeArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.IntProvider;

public class ESWeatherCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandBuildContext) {
        return Commands.literal("weather").requires((commandSourceStack) -> {
            return commandSourceStack.hasPermission(2);
        }).then(Commands.argument("weather", ResourceArgument.resource(commandBuildContext, WeatherInit.REGISTRY_KEY)).executes((commandContext) -> {
            return setWeather(commandContext.getSource(), ResourceArgument.getResource(commandContext, "weather", WeatherInit.REGISTRY_KEY).value(), -1);
        }).then(Commands.argument("duration", TimeArgument.time(1)).executes((commandContext) -> {
            return setWeather(commandContext.getSource(), ResourceArgument.getResource(commandContext, "weather", WeatherInit.REGISTRY_KEY).value(), IntegerArgumentType.getInteger(commandContext, "duration"));
        }))).then(Commands.literal("clear").executes((commandContext) -> {
            return setClear(commandContext.getSource(), -1);
        }).then(Commands.argument("duration", TimeArgument.time(1)).executes((commandContext) -> {
            return setClear(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "duration"));
        })));
    }

    private static int getDuration(CommandSourceStack commandSourceStack, int i, IntProvider intProvider) {
        return i == -1 ? intProvider.sample(commandSourceStack.getLevel().getRandom()) : i;
    }

    private static int setWeather(CommandSourceStack commandSourceStack, AbstractWeather weather, int duration) {
        int trueDuration = getDuration(commandSourceStack, duration, weather.weatherProperties().duration());
        if (trueDuration > 0 && commandSourceStack.getLevel().dimension() == DimensionInit.STARLIGHT_KEY) {
            WeatherUtil.getOrCreateWeathers(commandSourceStack.getLevel()).setActiveWeather(weather, trueDuration);
            commandSourceStack.sendSuccess(() -> Component.translatable("commands.eternal_starlight.weather.set", weather.getDescription()), true);
        } else {
            commandSourceStack.sendFailure(Component.translatable("commands.eternal_starlight.weather.fail", weather.getDescription()));
        }
        return duration;
    }

    private static int setClear(CommandSourceStack commandSourceStack, int duration) {
        int trueDuration = getDuration(commandSourceStack, duration, ServerLevel.RAIN_DELAY);
        if (trueDuration > 0 && commandSourceStack.getLevel().dimension() == DimensionInit.STARLIGHT_KEY) {
            WeatherUtil.getOrCreateWeathers(commandSourceStack.getLevel()).clearAllWeathers(trueDuration);
            commandSourceStack.sendSuccess(() -> Component.translatable("commands.eternal_starlight.weather.clear"), true);
        } else {
            commandSourceStack.sendFailure(Component.translatable("commands.eternal_starlight.weather.clear_fail"));
        }
        return duration;
    }
}
