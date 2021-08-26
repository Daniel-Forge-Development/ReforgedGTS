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

    private Messages messages = new Messages();

    private String guiName = "GTS";
    private ItemSetting pokemonItem = new ItemSetting("&ePokemon", Lists.newArrayList(
            "&e&nClick me&7 to add a pokemon to",
            "&7GTS from your party."
    ));
    private ItemSetting itemItem = new ItemSetting("&eItems", Lists.newArrayList(
            "&e&nClick me&7 to add a items to",
            "&7GTS from your inventory."
    ));

    public LocaleConfig() {
        super();
    }

    public Messages getMessages() {
        return this.messages;
    }

    public String getGuiName() {
        return UtilChatColour.translateColourCodes('&', this.guiName);
    }

    public ItemSetting getItemItem() {
        return this.itemItem;
    }

    public ItemSetting getPokemonItem() {
        return this.pokemonItem;
    }

    @ConfigSerializable
    public class ItemSetting {

        private String name;
        private List<String> lore;
        private transient List<String> cachedLore = null;

        public ItemSetting() {}

        public ItemSetting(String name, List<String> lore) {
            this.name = name;
            this.lore = lore;
        }

        public String getName() {
            return UtilChatColour.translateColourCodes('&', this.name);
        }

        public List<String> getLore() {
            if (this.cachedLore == null) {
                List<String> lore = Lists.newArrayList();

                for (String s : this.lore) {
                    lore.add(UtilChatColour.translateColourCodes('&', s));
                }

                this.cachedLore = lore;
            }

            return this.cachedLore;
        }
    }

    @ConfigSerializable
    public class Messages {

        private String openingUi = "&e&l(!) &eOpening GTS...";
        private String sellInsuffucientArgs = "&c&l(!) &cInsufficient args! /gts s <amount> <price>";
        private String sellNoItemInHand = "&c&l(!) &cYou must have an item in your hand to sell!";

        private String amountMustBePositive = "&c&l(!) &cAmount must be a positive number!";
        private String priceMustBeMoreThanOne = "&c&l(!) &cPrice cannot be less than $1";

        public Messages() {}

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
    }
}
