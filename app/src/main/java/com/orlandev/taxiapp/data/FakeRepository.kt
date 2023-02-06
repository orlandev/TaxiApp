package com.orlandev.taxiapp.data

import kotlin.math.roundToInt
import kotlin.random.Random

class FakeRepository {

    fun getAllTaxiData(): List<TaxiData> {
        return listOf(
            TaxiData(
                id = 0,
                type = TaxiType.Basic,
                distance = Random.nextInt(0, 100),
                price = Random.nextInt(10, 100),
                rating = (Random.nextDouble(1.0, 5.0) * 100.0).roundToInt() / 100.0
            ),

            TaxiData(
                id = 1,
                type = TaxiType.Lux,
                distance = Random.nextInt(0, 100),
                price = Random.nextInt(10, 100),
                rating = (Random.nextDouble(1.0, 5.0) * 100.0).roundToInt() / 100.0
            ),

            TaxiData(
                id = 2,
                type = TaxiType.Basic,
                distance = Random.nextInt(0, 100),
                price = Random.nextInt(10, 100),
                rating = (Random.nextDouble(1.0, 5.0) * 100.0).roundToInt() / 100.0
            ),

            TaxiData(
                id = 3,
                type = TaxiType.Basic,
                distance = Random.nextInt(0, 100),
                price = Random.nextInt(10, 100),
                rating = (Random.nextDouble(1.0, 5.0) * 100.0).roundToInt() / 100.0
            ),

            TaxiData(
                id = 4,
                type = TaxiType.Lux,
                distance = Random.nextInt(0, 100),
                price = Random.nextInt(10, 100),
                rating = (Random.nextDouble(1.0, 5.0) * 100.0).roundToInt() / 100.0
            ),

            )
    }

}