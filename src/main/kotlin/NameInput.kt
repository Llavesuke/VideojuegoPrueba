import java.awt.Color
import java.awt.GridBagLayout
import javax.swing.*
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent

class NameInput(private val gameWindow: GameWindow) : JPanel() {
    private val textField: JTextField = JTextField(20)

    init {
        layout = GridBagLayout()
        background = Color.BLACK

        val nameLabel = JLabel("Name: ")
        nameLabel.foreground = Color.WHITE

        textField.background = null
        textField.border = null
        textField.foreground = Color.WHITE
        textField.addActionListener {
            gameWindow.playerName = textField.text
            gameWindow.switchToCharacterMenu()
        }

        // Add a FocusListener
        textField.addFocusListener(object : FocusAdapter() {
            override fun focusLost(e: FocusEvent?) {
                gameWindow.playerName = textField.text
                println(gameWindow.playerName)
            }
        })

        val inputPanel = JPanel() // Create a new JPanel
        inputPanel.layout = BoxLayout(inputPanel, BoxLayout.X_AXIS)
        inputPanel.background = Color.BLACK
        inputPanel.border = BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(Color.WHITE, 2)
        )
        inputPanel.add(nameLabel)
        inputPanel.add(textField)

        add(inputPanel)
    }

    fun getInput(): String {
        return textField.text
    }
}