class Player(val name: String, val characterClass: String, val race: String) {

    var observer: PlayerStatsObserver? = null

    var money = 0

    var inventory = mutableListOf<String>()

    var health = 0
        set(value) {
            field = value + (constitution*0.5).toInt()
        }

    var level = 1
        set(value) {
            field = value
            observer?.onPlayerStatsChanged(this)
        }
    var experience = 0
        set(value) {
            field = value
            observer?.onPlayerStatsChanged(this)
        }
    var maxExperience = 10
        set(value) {
            field = value
            observer?.onPlayerStatsChanged(this)
        }

    var strength = 0
        set(value) {
            field = value + (constitution*0.2).toInt()
            observer?.onPlayerStatsChanged(this)
        }
    var dexterity = 0
        set(value) {
            field = value + (constitution*0.1).toInt()
            observer?.onPlayerStatsChanged(this)
        }
    var constitution = 0
        set(value) {
            field = value
            observer?.onPlayerStatsChanged(this)
        }

    val attack: Int
        get() = strength * 2


    init {
        setInitialStats()
    }

    fun updateExperience(amount: Int){
        experience += amount
        if (experience >= maxExperience){
            level++
            experience -= maxExperience
            maxExperience += 10
        }
    }

    fun takeDamage(damage: Int){
        health -= damage
        if (health <= 0){
            //TODO Game Over
        }
    }

    private fun setInitialStats(){
        when (race){
            "Human" -> {
                health = 100
                strength += 10
                dexterity += 5
                constitution += 2
            }
            "Alien" -> {
                health = 200
                strength += 15
                dexterity += 2
                constitution += 3
            }
            "Cyborg" -> {
                health = 150
                strength += 15
                dexterity += 7
                constitution += 4
            }
        }

        when (characterClass){
            "Spacial Knight" -> {
                strength += 5
                dexterity += 1
                constitution += 2
                //TODO Add starter items & equipment
            }
            "Shooter" -> {
                strength += 1
                dexterity += 4
                constitution += 2
                //TODO Add starter items & equipment
            }

            "Colonel" -> {
                strength += 2
                dexterity += 2
                constitution += 2
                //TODO Add starter items & equipment
            }

        }

        fun levelUp(){
            level++
        }
    }
}