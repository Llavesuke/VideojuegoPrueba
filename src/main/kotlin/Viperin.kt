class Viperin(): Monster {
    override val monsterName = "Viperin"
    override var monsterHealth = 20
    override val monsterAttack = 4
    override val experience: Int = 3

    override fun takeDamage(damage: Int): Boolean {
        var deadMonster = false
        monsterHealth -= damage
        if (monsterHealth <= 0){
            monsterHealth = 0
            deadMonster = true
        }

        return deadMonster
    }

    override fun attack(player: Player) {
        player.takeDamage(monsterAttack)
    }

    override fun dropLoot(): String {
        return "Viperin Fang"
    }
}