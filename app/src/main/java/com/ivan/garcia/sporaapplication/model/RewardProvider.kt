package com.ivan.garcia.sporaapplication.model

class RewardProvider {
    companion object {
        fun randomReward(): Reward {
            val position = (0..10).random()
            return rewards[position]
        }

        private val rewards = listOf(
            Reward(
                "ABDGD5S0SN",
                "10% De descuento en tu proxima compra."
            ),
            Reward(
                "-",
                "Gracias por participar"
            ),
            Reward(
                "8s8d0l",
                "$20 pesos de descuento"
            ),
            Reward(
                "IS93LJS",
                "20% De descuento en tu proxima compra."
            ),
            Reward(
                "MS82P0SF7",
                "Envio gratis."
            ),
            Reward(
                "-",
                "Gracias por participar"
            ),
            Reward(
                "92LGS69BJS",
                "2x1 en compras de juguetes"
            ),
            Reward(
                "02JS8DN",
                "70% De descuento en tu proxima compra"
            ),
            Reward(
                "-",
                "Gracias por participar"
            ),
            Reward(
                "02SBTS79",
                "$100 pesos de descuento"
            ),
        )
    }
}