interface Monster{
    val monsterName: String
    val monsterHealth: Int
    val monsterAttack: Int
    val experience: Int

    fun takeDamage(damage: Int): Boolean
    fun attack(player: Player)
    fun dropLoot(): String
}