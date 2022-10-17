package com.envyful.gts.forge.ui;

import com.envyful.api.config.type.ConfigItem;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.config.UtilConfigItem;
import com.envyful.api.gui.factory.GuiFactory;
import com.envyful.api.gui.pane.Pane;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.gts.api.Trade;
import com.envyful.gts.api.gui.FilterType;
import com.envyful.gts.api.gui.FilterTypeFactory;
import com.envyful.gts.api.gui.SortType;
import com.envyful.gts.forge.EnvyGTSForge;
import com.envyful.gts.forge.config.GuiConfig;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ViewTradesUI {

    public static void openUI(EnvyPlayer<EntityPlayerMP> player) {
        openUI(player, 0, FilterTypeFactory.getDefault(), SortType.MOST_RECENT);
    }

    public static void openUI(EnvyPlayer<EntityPlayerMP> player, int page, FilterType filter, SortType sort) {
        GuiConfig.SearchTradesConfig config = EnvyGTSForge.getInstance().getGui().getSearchUIConfig();

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

        List<Trade> allTrades = EnvyGTSForge.getInstance().getTradeManager().getAllTrades();

        allTrades.removeIf(trade -> {
            if (trade.wasPurchased()) {
                return true;
            }

            if (trade.hasExpired() || trade.wasRemoved()) {
                return true;
            }

            return !trade.filter(player, filter);
        });

        allTrades.sort((o1, o2) -> o1.compare(o2, sort));
//
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
//                                 if ((page + 1) > (allTrades.size() / 45)) {
//                                     openUI(player, 0, filter, sort);
//                                 } else {
//                                     openUI(player, page + 1, filter, sort);
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
//                                     openUI(player, (allTrades.size() / 45), filter, sort);
//                                 } else {
//                                     openUI(player, page - 1, filter, sort);
//                                 }
//                             })
//                             .build()
//            );
//        }
//
//        if (config.getOrderButton().isEnabled()) {
//            pane.set(config.getOrderButton().getXPos(), config.getOrderButton().getYPos(),
//                     GuiFactory.displayableBuilder(ItemStack.class)
//                             .itemStack(new ItemBuilder(UtilConfigItem.fromConfigItem(config.getOrderButton()))
//                                                .lore(formatButtonLores(config.getOrderButton().getLore(), filter, sort))
//                                                .build())
//                             .clickHandler((envyPlayer, clickType) -> openUI(player, page, filter, sort.getNext()))
//                             .build()
//            );
//        }
//
//        if (config.getFilterButton().isEnabled()) {
//            pane.set(config.getFilterButton().getXPos(), config.getFilterButton().getYPos(),
//                     GuiFactory.displayableBuilder(ItemStack.class)
//                             .itemStack(new ItemBuilder(UtilConfigItem.fromConfigItem(config.getFilterButton()))
//                                                .lore(formatButtonLores(config.getFilterButton().getLore(), filter, sort))
//                                                .build())
//                             .clickHandler((envyPlayer, clickType) -> openUI(player, page, filter.getNext(), sort))
//                             .build()
//            );
//        }

        for (int i = (page * 45); i < ((page + 1) * 45); i++) {
            if (i >= allTrades.size()) {
                continue;
            }

            Trade trade = allTrades.get(i);
            trade.display(i % 45, pane);
        }

        GuiFactory.guiBuilder()
                .setPlayerManager(EnvyGTSForge.getInstance().getPlayerManager())
                .addPane(pane)
                .setCloseConsumer(envyPlayer -> {})
                .height(config.getGuiSettings().getHeight())
                .title(UtilChatColour.translateColourCodes('&', config.getGuiSettings().getTitle()))
                .build().open(player);
    }

    private static List<String> formatButtonLores(List<String> lore, FilterType filterType, SortType sortType) {
        List<String> newLore = Lists.newArrayList();

        for (String s : lore) {
            newLore.add(UtilChatColour.translateColourCodes('&', s
                    .replace("%filter%", filterType.getDisplayName())
                    .replace("%order%", sortType.getDisplayName())));
        }

        return newLore;
    }
}
