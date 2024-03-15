import javax.swing.*
import java.awt.*
import java.awt.event.*

class CharacterMenu(private val gameWindow: GameWindow) : JPanel() {
    private val textField: JTextField = JTextField(20)
    val options: Array<String> = arrayOf("Human", "Alien", "Cyborg")
    var selectedIndex = 0
    var selectedClass: String = ""
    val menuLabels = mutableListOf<JLabel>()

    init {
        layout = GridBagLayout()
        background = Color.BLACK

        setupMenu()

        addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_UP -> moveSelectionUp()
                    KeyEvent.VK_DOWN -> moveSelectionDown()
                    KeyEvent.VK_ENTER -> handleMenuOption()
                }
            }
        })
        isFocusable = true
    }

    fun setupMenu() {
        val menuPanel = JPanel(GridBagLayout())
        menuPanel.background = Color.BLACK
        menuPanel.border = BorderFactory.createLineBorder(Color.WHITE, 2)

        val gbc = GridBagConstraints() // Define gbc here
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.fill = GridBagConstraints.HORIZONTAL

        options.forEachIndexed { index, option ->
            val label = JLabel(option)
            label.font = Font("Monospaced", Font.PLAIN, 16)
            label.horizontalAlignment = SwingConstants.CENTER
            label.border = if (index == 1) {
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
            } else {
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
            }

            if (index == 0) {
                label.foreground = Color.BLACK
                label.background = Color.WHITE
                label.isOpaque = true
            } else {
                label.foreground = Color.WHITE
            }

            menuPanel.add(label, gbc)
            menuLabels.add(label)
            gbc.gridy++
        }

        add(menuPanel)
    }

    fun moveSelectionUp() {
        if (selectedIndex > 0) {
            menuLabels[selectedIndex].foreground = Color.WHITE
            menuLabels[selectedIndex].isOpaque = false
            selectedIndex--
            menuLabels[selectedIndex].foreground = Color.BLACK
            menuLabels[selectedIndex].background = Color.WHITE
            menuLabels[selectedIndex].isOpaque = true
        }
    }

    fun moveSelectionDown() {
        if (selectedIndex < options.size - 1) {
            menuLabels[selectedIndex].foreground = Color.WHITE
            menuLabels[selectedIndex].isOpaque = false
            selectedIndex++
            menuLabels[selectedIndex].foreground = Color.BLACK
            menuLabels[selectedIndex].background = Color.WHITE
            menuLabels[selectedIndex].isOpaque = true
        }
    }

    fun handleMenuOption() {
        selectedClass = when (selectedIndex) {
            0 -> "Human"
            1 -> "Alien"
            2 -> "Cyborg"
            else -> ""
        }
        gameWindow.selectedRace = selectedClass
        println(gameWindow.selectedRace)
        gameWindow.switchToClassMenu()
    }

    fun classReturned(): String {
        return selectedClass
    }
}