package com.envyful.gts.forge.ui;

import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.gts.api.Trade;
import com.envyful.gts.forge.EnvyGTSForge;
import com.envyful.gts.forge.config.GuiConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import java.util.List;

public class TimedOutTradesUI {

    public static void openUI(EnvyPlayer<EntityPlayerMP> player) {
        openUI(player, 0);
    }

    public static void openUI(EnvyPlayer<EntityPlayerMP> player, int page) {
        GuiConfig.TimedOutTradesConfig config = EnvyGTSForge.getInstance().getGui().getTimedOutUIConfig();

        Pane pane = GuiFactory.paneBuilder()
                .topLeftX(0)
                .topLeftY(0)
                .width(9)
                .height(config.getGuiSettings().getHeight())
                .build();

        for (ConfigItem fillerItem : config.getGuiSettings().getFillerItems()) {
            pane.add(GuiFactory.displayableBuilder(ItemStack.class)
                             .itemStack(UtilConfigItem.fromConfigItem(fillerItem))
                             .build());
        }

        List<Trade> allTrades = EnvyGTSForge.getInstance().getTradeManager().getExpiredTrades(player);

//        if (config.getBackButton().isEnabled()) {
//            pane.set(config.getBackButton().getXPos(), config.getBackButton().getYPos(),
//                     GuiFactory.displayableBuilder(ItemStack.class)
//                             .itemStack(UtilConfigItem.fromConfigItem(config.getBackButton()))
//                             .clickHandler((envyPlayer, clickType) -> MainUI.open(player))
//                             .build()
//            );
//        }
//
//        if (config.getNextPageItem().isEnabled()) {
//            pane.set(config.getNextPageItem().getXPos(), config.getNextPageItem().getYPos(),
//                     GuiFactory.displayableBuilder(ItemStack.class)
//                             .itemStack(UtilConfigItem.fromConfigItem(config.getNextPageItem()))
//                             .clickHandler((envyPlayer, clickType) -> {
//                                 if ((page + 1) >= (allTrades.size() / 45)) {
//                                     openUI(player, 0);
//                                 } else {
//                                     openUI(player, page - 1);
//                                 }
//                             })
//                             .build()
//            );
//        }
//
//        if (config.getPreviousPageItem().isEnabled()) {
//            pane.set(config.getPreviousPageItem().getXPos(), config.getPreviousPageItem().getYPos(),
//                     GuiFactory.displayableBuilder(ItemStack.class)
//                             .itemStack(UtilConfigItem.fromConfigItem(config.getPreviousPageItem()))
//                             .clickHandler((envyPlayer, clickType) -> {
//                                 if (page == 0) {
//                                     openUI(player, (allTrades.size() / 45));
//                                 } else {
//                                     openUI(player, page - 1);
//                                 }
//                             })
//                             .build()
//            );
//        }

        for (int i = (page * 45); i < ((page + 1) * 45); i++) {
            if (i >= allTrades.size()) {
                continue;
            }

            Trade trade = allTrades.get(i);
            trade.displayClaimable(i % 45, clicker -> TimedOutTradesUI.openUI((EnvyPlayer<EntityPlayerMP>) clicker, page), pane);
        }

        GuiFactory.guiBuilder()
                .setPlayerManager(EnvyGTSForge.getInstance().getPlayerManager())
                .addPane(pane)
                .setCloseConsumer(envyPlayer -> {})
                .height(config.getGuiSettings().getHeight())
                .title(UtilChatColour.translateColourCodes('&', config.getGuiSettings().getTitle()))
                .build().open(player);
    }
}
