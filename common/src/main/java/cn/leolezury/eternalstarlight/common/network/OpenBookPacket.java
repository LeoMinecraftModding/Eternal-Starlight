package cn.leolezury.eternalstarlight.common.network;

import cn.leolezury.eternalstarlight.common.client.gui.screens.BookRenderData;
import cn.leolezury.eternalstarlight.common.client.gui.screens.ESBookScreen;
import cn.leolezury.eternalstarlight.common.manager.book.BookData;
import cn.leolezury.eternalstarlight.common.manager.book.chapter.ChapterData;
import cn.leolezury.eternalstarlight.common.platform.ESPlatform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OpenBookPacket {
    private BookData bookData;
    private List<ChapterData> chapterDataList;

    public OpenBookPacket(BookData bookData, List<ChapterData> chapterDataList) {
        this.bookData = bookData;
        this.chapterDataList = chapterDataList;
    }

    public static OpenBookPacket read(FriendlyByteBuf buf) {
        BookData.Builder builder = new BookData.Builder();
        builder.withWidth(buf.readInt());
        builder.withHeight(buf.readInt());
        builder.withTextOffsetX(buf.readInt());
        builder.withTextOffsetY(buf.readInt());
        builder.withBackground(new ResourceLocation(buf.readUtf(384)));
        builder.withTitleBackground(new ResourceLocation(buf.readUtf(384)));
        builder.withTitle(buf.readUtf(384));
        int size = buf.readInt();
        List<ChapterData> dataList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ChapterData.Builder chapterBuilder = new ChapterData.Builder();
            chapterBuilder.withImage(new ResourceLocation(buf.readUtf(384)));
            chapterBuilder.withEntityDisplay(new ResourceLocation(buf.readUtf(384)));
            chapterBuilder.withTitle(buf.readUtf(384));
            chapterBuilder.withContent(buf.readUtf(384));
            chapterBuilder.withDisplayScale(buf.readFloat());
            chapterBuilder.withEntityOffset(buf.readInt());
            dataList.add(chapterBuilder.build());
        }
        return new OpenBookPacket(builder.build(), dataList);
    }

    public static void write(OpenBookPacket message, FriendlyByteBuf buf) {
        buf.writeInt(message.bookData.bookWidth);
        buf.writeInt(message.bookData.bookHeight);
        buf.writeInt(message.bookData.textOffsetX);
        buf.writeInt(message.bookData.textOffsetY);
        buf.writeUtf(message.bookData.getBackgroundLocation().toString(), 384);
        buf.writeUtf(message.bookData.getTitleBackgroundLocation().toString(), 384);
        buf.writeUtf(message.bookData.getTitle(), 384);
        buf.writeInt(message.chapterDataList.size());
        for (ChapterData chapterData : message.chapterDataList) {
            buf.writeUtf(chapterData.getImageLocation().toString(), 384);
            buf.writeUtf(chapterData.getDisplayEntity().toString(), 384);
            buf.writeUtf(chapterData.getTitle(), 384);
            buf.writeUtf(chapterData.getContent(), 384);
            buf.writeFloat(chapterData.entityDisplayScale);
            buf.writeInt(chapterData.entityOffset);
        }
    }

    public static class Handler {
        public static void handle(OpenBookPacket message) {
            if (ESPlatform.INSTANCE.isClientSide()) {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                if (localPlayer != null) {
                    List<BookRenderData.ChapterRenderData> chapterRenderDataList = getRenderDataList(message);
                    BookRenderData bookRenderData = new BookRenderData(message.bookData.getBackgroundLocation(), message.bookData.getTitleBackgroundLocation(), Component.translatable(message.bookData.getTitle()), chapterRenderDataList, message.bookData.bookWidth, message.bookData.bookHeight, message.bookData.textOffsetX, message.bookData.textOffsetY);
                    Minecraft.getInstance().setScreen(new ESBookScreen(bookRenderData));
                }
            }
        }

        @NotNull
        private static List<BookRenderData.ChapterRenderData> getRenderDataList(OpenBookPacket message) {
            List<BookRenderData.ChapterRenderData> chapterRenderDataList = new ArrayList<>();
            for (ChapterData data : message.chapterDataList) {
                BookRenderData.ChapterRenderData chapterRenderData = new BookRenderData.ChapterRenderData(data.getDisplayEntity(), data.getImageLocation(), Component.translatable(data.getTitle()), Component.translatable(data.getContent()), data.entityDisplayScale, data.entityOffset);
                chapterRenderDataList.add(chapterRenderData);
            }
            return chapterRenderDataList;
        }
    }
}