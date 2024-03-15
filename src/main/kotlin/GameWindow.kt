import java.awt.CardLayout
import javax.swing.JFrame
import javax.swing.JPanel
import java.util.concurrent.CountDownLatch

class GameWindow : JFrame() {
    private val cards = JPanel(CardLayout())

    private val latch = CountDownLatch(1)



    var playerName: String? = null
    var selectedClass: String? = null
    var selectedRace: String? = null
    var player: Player? = null

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isUndecorated = true
        extendedState = JFrame.MAXIMIZED_BOTH
        isResizable = false

        // Add the cards panel to the GameWindow
        add(cards)

        pack()
        isVisible = true
    }

    fun switchToNameInput() {
        val nameInput = NameInput(this)
        cards.add(nameInput, "NameInput")
        (cards.layout as CardLayout).show(cards, "NameInput")
    }

    fun switchToClassMenu() {
        val classMenu = ClassMenu(this)
        cards.add(classMenu, "ClassMenu")
        (cards.layout as CardLayout).show(cards, "ClassMenu")
        classMenu.requestFocusInWindow()

    }

    fun switchToLoadingScreen() {
        val loadingScreen = LoadingScreen()
        cards.add(loadingScreen, "LoadingScreen")
        (cards.layout as CardLayout).show(cards, "LoadingScreen")
    }

    fun switchToCharacterMenu() {
        val raceMenu = CharacterMenu(this)
        cards.add(raceMenu, "RaceMenu")
        (cards.layout as CardLayout).show(cards, "RaceMenu")
        raceMenu.requestFocusInWindow()
    }


    fun constructPlayer() {
        if (playerName != null && selectedClass != null && selectedRace != null) {
            player = Player(playerName!!, selectedClass!!, selectedRace!!)
        }
    }
}