package com.envyful.reforged.gts.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.envyful.api.forge.chat.UtilChatColour;
import com.google.common.collect.Lists;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;

@ConfigPath("config/ReforgedGTS/locale.yml")
@ConfigSerializable
public class LocaleConfig extends AbstractYamlConfig {

    private List<String> listingBelowDataLore = Lists.newArrayList(
            "  ",
            "&bSeller: %owner%",
            "&bCost: &a$%cost%",
            "&bRemaining Time: &e%duration%"
    );

    private List<String> listingBelowExpiredOrClaimableLore = Lists.newArrayList(
            "  ",
            "&bSeller: %original_owner%",
            "&bCost: &a$%cost%"
    );

    private String moneyFormat = "%.2f";

    private Messages messages = new Messages();

    public LocaleConfig() {
        super();
    }

    public Messages getMessages() {
        return this.messages;
    }

    public String getMoneyFormat() {
        return this.moneyFormat;
    }

    public List<String> getListingBelowDataLore() {
        return this.listingBelowDataLore;
    }

    public List<String> getListingBelowExpiredOrClaimableLore() {
        return this.listingBelowExpiredOrClaimableLore;
    }

    @ConfigSerializable
    public static class Messages {

        private String openingUi = "&e&l(!) &eOpening GTS...";
        private String sellInsuffucientArgs = "&c&l(!) &cInsufficient args! /gts s <amount> <price> [duration (seconds)]";
        private String sellNoItemInHand = "&c&l(!) &cYou must have an item in your hand to sell!";

        private String amountMustBePositive = "&c&l(!) &cAmount must be a positive number!";
        private String priceMustBeMoreThanOne = "&c&l(!) &cPrice cannot be less than $1";
        private String inventoryFull = "&c&l(!) &cYour inventory is full!";
        private String insufficientFunds = "&c&l(!) &cYou don't have enough money!";
        private String maxTradesAlreadyReached = "&c&l(!) &cYou cannot add anymore trades to the GTS";
        private String notEnoughItems = "&c&l(!) &cYou don't have enough in your hand to sell this amount!";
        private String cannotSellBlacklisted = "&c&l(!) &cYou cannot sell this item as it's blacklisted!";
        private String cannotGoAboveMaxPrice = "&c&l(!) &cYou cannot sell for more than $%max_price%";
        private String cannotGoBelowMinTime = "&c&l(!) &cYou cannot sell for less than %min_duration% seconds";

        private String addedItemToGts = "&e&l(!) &eSuccessfully listed item on GTS";
        private String adminRemoveTrade = "&e&l(!) &eYou successfully removed the trade from the GTS";
        private String purchasedTrade = "&e&l(!) &eSuccessfully purchased trade from GTS";
        private String removedOwnTrade = "&e&l(!) &eSuccessfully removed your trade";

        private String toggledBroadcastsOn = "&e&l(!) &eToggled broadcasts &a&lON";
        private String toggledBroadcastsOff = "&e&l(!) &eToggled broadcasts &c&lOFF";

        private String itemWasPurchased = "&e&l(!) &eYour %item% auction was purchased by %buyer% for %price% and &a$%tax%&e was taken!";
        private String itemsToClaim = "&c&l(!) &cYou have auctions to claim in the GTS!";

        private List<String> createTradeBroadcast = Lists.newArrayList(
                " ",
                "&a&lREFORGED GTS",
                "&e%player%&7 added a new GTS listing for %name% for $%cost%",
                " "
        );

        public Messages() {}

        public String getCannotSellBlacklisted() {
            return this.cannotSellBlacklisted;
        }

        public String getRemovedOwnTrade() {
            return this.removedOwnTrade;
        }

        public String getInsufficientFunds() {
            return this.insufficientFunds;
        }

        public String getPurchasedTrade() {
            return this.purchasedTrade;
        }

        public String getAdminRemoveTrade() {
            return this.adminRemoveTrade;
        }

        public String getInventoryFull() {
            return this.inventoryFull;
        }

        public String getOpeningUi() {
            return UtilChatColour.translateColourCodes('&', this.openingUi);
        }

        public String getSellInsuffucientArgs() {
            return UtilChatColour.translateColourCodes('&', this.sellInsuffucientArgs);
        }

        public String getSellNoItemInHand() {
            return UtilChatColour.translateColourCodes('&', this.sellNoItemInHand);
        }

        public String getAmountMustBePositive() {
            return UtilChatColour.translateColourCodes('&', this.amountMustBePositive);
        }

        public String getPriceMustBeMoreThanOne() {
            return UtilChatColour.translateColourCodes('&', this.priceMustBeMoreThanOne);
        }

        public String getAddedItemToGts() {
            return this.addedItemToGts;
        }

        public String getToggledBroadcastsOn() {
            return this.toggledBroadcastsOn;
        }

        public String getToggledBroadcastsOff() {
            return this.toggledBroadcastsOff;
        }

        public List<String> getCreateTradeBroadcast() {
            return this.createTradeBroadcast;
        }

        public String getMaxTradesAlreadyReached() {
            return this.maxTradesAlreadyReached;
        }

        public String getNotEnoughItems() {
            return this.notEnoughItems;
        }

        public String getCannotGoAboveMaxPrice() {
            return this.cannotGoAboveMaxPrice;
        }

        public String getCannotGoBelowMinTime() {
            return this.cannotGoBelowMinTime;
        }

        public String getItemWasPurchased() {
            return this.itemWasPurchased;
        }

        public String getItemsToClaim() {
            return this.itemsToClaim;
        }
    }
}
