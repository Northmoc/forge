package forge.screens.match.views;

import forge.GuiBase;
import forge.LobbyPlayer;
import forge.assets.FSkinImage;
import forge.deck.Deck;
import forge.deck.FDeckViewer;
import forge.game.io.GameStateDeserializer;
import forge.game.io.GameStateSerializer;
import forge.menu.FDropDownMenu;
import forge.menu.FMenuItem;
import forge.properties.ForgeConstants;
import forge.screens.match.FControl;
import forge.screens.settings.SettingsScreen;
import forge.toolbox.FEvent;
import forge.toolbox.FOptionPane;
import forge.toolbox.FEvent.FEventHandler;

public class VGameMenu extends FDropDownMenu {
    @Override
    protected void buildMenu() {
        addItem(new FMenuItem("Concede", FSkinImage.CONCEDE, new FEventHandler() {
            @Override
            public void handleEvent(FEvent e) {
                FControl.concede();
            }
        }));
        addItem(new FMenuItem("Save Game", FSkinImage.SAVE, new FEventHandler() {
            @Override
            public void handleEvent(FEvent e) {
                GameStateSerializer.saveGameState(FControl.getGame(), ForgeConstants.USER_GAMES_DIR + "GameSave.txt");
                FOptionPane.showMessageDialog("Game saved successfully.", "Save Game", FOptionPane.INFORMATION_ICON);
            }
        }));
        addItem(new FMenuItem("Load Game", FSkinImage.OPEN, new FEventHandler() {
            @Override
            public void handleEvent(FEvent e) {
                GameStateDeserializer.loadGameState(FControl.getGame(), ForgeConstants.USER_GAMES_DIR + "GameSave.txt");
            }
        }));
        addItem(new FMenuItem("Deck List", FSkinImage.DECKLIST, new FEventHandler() {
            @Override
            public void handleEvent(FEvent e) {
                final LobbyPlayer guiPlayer = GuiBase.getInterface().getGuiPlayer();
                final Deck deck = FControl.getGameView().getDeck(guiPlayer);
                if (deck != null) {
                    FDeckViewer.show(deck);
                }
            }
        }));
        addItem(new FMenuItem("Settings", FSkinImage.SETTINGS, new FEventHandler() {
            @Override
            public void handleEvent(FEvent e) {
                SettingsScreen.show();
            }
        }));
    }
}
