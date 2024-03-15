import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*
import javax.swing.border.LineBorder

class GameFrame(val player: Player) : JFrame(), PlayerStatsObserver {
    private lateinit var mapTextArea: JTextArea
    private val viewWidth = 113
    private val viewHeight = 40

    var levelLabel: JLabel = JLabel()
    var experienceLabel: JLabel = JLabel()
    var nameLabel: JLabel = JLabel()
    var strengthLabel: JLabel = JLabel()
    var dexterityLabel: JLabel = JLabel()
    var constitutionLabel: JLabel = JLabel()
    var inventoryLabel: JLabel = JLabel()

    //private lateinit var battleTextArea: JTextArea
    private var currentMonster: Monster? = null
    private var roundCounter = 0


    init {
        player.observer = this

        title = "Mapa"
        defaultCloseOperation = EXIT_ON_CLOSE
        isUndecorated = true
        extendedState = MAXIMIZED_BOTH
        isResizable = false
        isFocusable = true

        val mapWidth = 5000
        val mapHeight = 5000
        val mapa = Map(mapWidth, mapHeight)

        val contentPane = contentPane
        contentPane.layout = BorderLayout()

        mapTextArea = JTextArea(mapa.displayMap())
        mapTextArea.isEditable = false
        mapTextArea.font = Font("Monospaced", Font.CENTER_BASELINE, 20)
        mapTextArea.margin = Insets(0, 0, 0, 0)
        mapTextArea.background = Color.BLACK
        mapTextArea.foreground = Color.WHITE
        mapTextArea.border = LineBorder(Color.WHITE)
        contentPane.add(mapTextArea, BorderLayout.CENTER)

        /*
        battleTextArea = JTextArea()
        battleTextArea.isEditable = false
        battleTextArea.font = Font("Monospaced", Font.CENTER_BASELINE, 20)
        battleTextArea.background = Color.BLACK
        battleTextArea.foreground = Color.WHITE
        battleTextArea.border = LineBorder(Color.WHITE) // Agrega un borde blanco
        */
        addKeyListener(object : KeyListener {
            override fun keyPressed(e: KeyEvent?) {
                when (e?.keyCode) {
                    KeyEvent.VK_W -> mapa.movePlayer(0, -1)
                    KeyEvent.VK_A -> mapa.movePlayer(-1, 0)
                    KeyEvent.VK_S -> mapa.movePlayer(0, 1)
                    KeyEvent.VK_D -> mapa.movePlayer(1, 0)
                    /*
                    KeyEvent.VK_X -> {
                        val playerPosition = mapa.getPlayerPosition()
                        val positionsToCheck = listOf(
                            Pair(playerPosition.first - 1, playerPosition.second),
                            Pair(playerPosition.first, playerPosition.second - 1),
                            Pair(playerPosition.first + 1, playerPosition.second),
                            Pair(playerPosition.first, playerPosition.second + 1)
                        )

                        for (position in positionsToCheck) {
                            val mapChar = mapa.obtainCurrentMap().getOrNull(position.second)?.getOrNull(position.first)
                            if (mapChar == 'M') {
                                currentMonster = Viperin()
                                startBattle(currentMonster!!)
                                break
                            }
                        }
                    }
                    */
                }
                updateMap(mapa)
            }

            override fun keyTyped(e: KeyEvent?) {
                //This function is empty because we don't need doing anything with this
            }
            override fun keyReleased(e: KeyEvent?) {
                //This function is empty because we don't need doing anything with this
            }

        })
        requestFocusInWindow()
        isVisible = true


        onPlayerStatsChanged(player)

        val levelPanel = JPanel()
        levelPanel.layout = BoxLayout(levelPanel, BoxLayout.Y_AXIS)
        levelPanel.background = Color.BLACK
        levelPanel.border = LineBorder(Color.WHITE)
        levelPanel.add(levelLabel)
        levelPanel.add(experienceLabel)
        levelPanel.add(nameLabel)

        val statisticsPanel = JPanel()
        statisticsPanel.layout = BoxLayout(statisticsPanel, BoxLayout.Y_AXIS)
        statisticsPanel.preferredSize = Dimension(500, 20)
        statisticsPanel.background = Color.BLACK
        statisticsPanel.border = BorderFactory.createCompoundBorder(
            LineBorder(Color.WHITE),
            BorderFactory.createEmptyBorder(60, 10, 10, 10)
        )
        statisticsPanel.add(strengthLabel)
        statisticsPanel.add(Box.createVerticalStrut(10))
        statisticsPanel.add(dexterityLabel)
        statisticsPanel.add(Box.createVerticalStrut(10))
        statisticsPanel.add(constitutionLabel)

        val inventoryPanel = JPanel()
        inventoryPanel.layout = BoxLayout(inventoryPanel, BoxLayout.Y_AXIS)
        inventoryPanel.preferredSize = Dimension(500, 440)
        inventoryPanel.background = Color.BLACK
        inventoryPanel.border = LineBorder(Color.WHITE)
        inventoryPanel.add(inventoryLabel)

        val statsPanel = JPanel(BorderLayout())
        statsPanel.preferredSize = Dimension(500, height)
        statsPanel.background = Color.BLACK
        statsPanel.add(levelPanel, BorderLayout.NORTH)
        statsPanel.add(statisticsPanel, BorderLayout.CENTER)
        statsPanel.add(inventoryPanel, BorderLayout.SOUTH)
        contentPane.add(statsPanel, BorderLayout.EAST)

        val infoPanel = JPanel()
        infoPanel.preferredSize = Dimension(width, 280)
        infoPanel.background = Color.BLACK
        infoPanel.border = LineBorder(Color.WHITE)
        contentPane.add(infoPanel, BorderLayout.SOUTH)

        /*
        addKeyListener(object : KeyListener {
            override fun keyPressed(e: KeyEvent?) {
                when (e?.keyCode) {
                    // ... rest of your code ...

                    KeyEvent.VK_X -> {
                        val playerPosition = mapa.getPlayerPosition()
                        val positionsToCheck = listOf(
                            Pair(playerPosition.first - 1, playerPosition.second),
                            Pair(playerPosition.first, playerPosition.second - 1),
                            Pair(playerPosition.first + 1, playerPosition.second),
                            Pair(playerPosition.first, playerPosition.second + 1)
                        )

                        for (position in positionsToCheck) {
                            val mapChar = mapa.obtainCurrentMap().getOrNull(position.second)?.getOrNull(position.first)
                            if (mapChar == 'M') {
                                currentMonster = Viperin()
                                startBattle(currentMonster!!)
                                break
                            }
                        }
                    }
                }
                updateMap(mapa)
            }



            override fun keyTyped(e: KeyEvent?) {
                //This function is empty because we don't need doing anything with this
            }
            override fun keyReleased(e: KeyEvent?) {
                //This function is empty because we don't need doing anything with this
            }

        })
        */
        requestFocusInWindow()
        isVisible = true
    }

    override fun onPlayerStatsChanged(player: Player) {
        levelLabel.text = "Level " + player.level.toString()
        levelLabel.font = Font("Monospaced", Font.PLAIN, 20)
        levelLabel.foreground = Color.WHITE
        experienceLabel.text = "Experience: " + player.experience.toString() + "/" + player.maxExperience.toString()
        experienceLabel.font = Font("Monospaced", Font.PLAIN, 20)
        experienceLabel.foreground = Color.WHITE
        nameLabel.text = player.name
        nameLabel.font = Font("Monospaced", Font.PLAIN, 20)
        nameLabel.foreground = Color.WHITE
        strengthLabel.text = "STR: " + player.strength.toString()
        strengthLabel.font = Font("Monospaced", Font.PLAIN, 20)
        strengthLabel.foreground = Color.WHITE
        dexterityLabel.text = "DEX: " + player.dexterity.toString()
        dexterityLabel.font = Font("Monospaced", Font.PLAIN, 20)
        dexterityLabel.foreground = Color.WHITE
        constitutionLabel.text = "CON: " + player.constitution.toString()
        constitutionLabel.font = Font("Monospaced", Font.PLAIN, 20)
        constitutionLabel.foreground = Color.WHITE

        inventoryLabel.text = "Inventory: \n" + player.inventory.joinToString("\n")
    }

    private fun updateMap(map: Map) {
        val playerPosition = map.getPlayerPosition()
        val startX = (playerPosition.first - viewWidth / 2).coerceAtLeast(0)
        val startY = ((playerPosition.second - viewHeight / 2)+3).coerceAtLeast(0)

        val visibleMap = StringBuilder()
        for (y in startY until startY + viewHeight) {
            for (x in startX until startX + viewWidth) {
                val mapChar = map.obtainCurrentMap().getOrNull(y)?.getOrNull(x) ?: ' '
                if (mapChar == 'M'){

                }
                visibleMap.append(mapChar)
            }
            visibleMap.append('\n')
        }
        mapTextArea.text = visibleMap.toString()
    }

    /*
    private fun startBattle(monster: Monster) {
        requestFocusInWindow()
        currentMonster = monster
        contentPane.remove(mapTextArea)
        contentPane.add(battleTextArea, BorderLayout.CENTER)
        contentPane.revalidate() // layout components again
        contentPane.repaint() // repaint the content pane
        updateBattleScene()
    }

    private fun updateBattleScene() {
        val battleScene = StringBuilder()
        battleScene.append("Round: $roundCounter\n")
        battleScene.append("Player Health: ${player.health}\n")
        battleScene.append("Monster Health: ${currentMonster?.monsterHealth}\n")
        battleScene.append("1. Attack\n")
        battleScene.append("2. Defend\n")
        battleScene.append("3. Run\n")
        battleTextArea.text = battleScene.toString()
    }

     */
}