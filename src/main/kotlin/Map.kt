class Map(width: Int, height: Int) {
    private val wallChar = '#'
    private val emptyChar = '.'
    private val playerChar = '@'
    private val monsterChar = 'M'

    private val mapWidth = width
    private val mapHeight = height
    private var playerX = mapWidth / 2
    private var playerY = mapHeight / 2

    private val viewWidth = 113
    private val viewHeight = 40

    private var currentMap: Array<CharArray> = Array(mapHeight) { CharArray(mapWidth) }

    private val monsters = 1

    init {
        generateMap()
    }

    private fun generateMap() {
        for (y in 0 until mapHeight) {
            for (x in 0 until mapWidth) {
                currentMap[y][x] = if (Math.random() < 0.05) wallChar else emptyChar
            }
        }
        currentMap[playerY][playerX] = playerChar

        val positions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
        val chosenPosition = positions.shuffled().first()
        val monsterX = (playerX + chosenPosition.first).coerceIn(0, mapWidth - 1)
        val monsterY = (playerY + chosenPosition.second).coerceIn(0, mapHeight - 1)
        if (currentMap[monsterY][monsterX] == emptyChar) {
            currentMap[monsterY][monsterX] = monsterChar
        }
    }

    fun movePlayer(dx: Int, dy: Int) {
        val newX = (playerX + dx).coerceIn(0, mapWidth - 1)
        val newY = (playerY + dy).coerceIn(0, mapHeight - 1)

        if (currentMap[newY][newX] == wallChar || currentMap[newY][newX] == monsterChar) {
            return
        }

        currentMap[playerY][playerX] = emptyChar
        playerX = newX
        playerY = newY
        currentMap[playerY][playerX] = playerChar
    }

    fun displayMap(): String {
        val visibleMap = StringBuilder()
        val startX = (playerX - viewWidth / 2).coerceAtLeast(0)
        val startY = ((playerY - viewHeight / 2)+3).coerceAtLeast(0)

        for (y in startY until startY + viewHeight) {
            for (x in startX until startX + viewWidth) {
                val mapChar = currentMap.getOrNull(y)?.getOrNull(x) ?: ' '
                visibleMap.append(mapChar)
            }
            visibleMap.append('\n')
        }
        return visibleMap.toString()
    }

    fun obtainCurrentMap(): Array<CharArray> {
        return currentMap
    }

    fun getPlayerPosition(): Pair<Int, Int> {
        return Pair(playerX, playerY)
    }
}