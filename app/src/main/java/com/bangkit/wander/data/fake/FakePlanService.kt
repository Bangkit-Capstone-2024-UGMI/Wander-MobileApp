package com.bangkit.wander.data.fake

import com.bangkit.wander.data.model.Plan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bangkit.wander.data.model.Hotel

class FakePlanService {

    private val planList = MutableLiveData<List<Plan>>()

    init {
        planList.value = listOf(
            Plan(
                "1",
                123,
                "Holiday Bro",
                "2021-10-10",
                "Bali, Indonesia",
                listOf(),
                Hotel(
                    "Hotel 1",
                    "Bali, Indonesia",
                    4.5,
                    4,
                    123.123,
                    1.9,
                    1.9,
                    "123"
                )
            ),
            Plan(
                "1",
                123,
                "Holiday Bro",
                "2021-10-10",
                "Bali, Indonesia",
                listOf(),
                Hotel(
                    "Hotel 1",
                    "Bali, Indonesia",
                    4.5,
                    4,
                    123.123,
                    1.9,
                    1.9,
                    "123"
                )
            ),
        )
    }

    fun getPlanList(): LiveData<List<Plan>> {
        return planList
    }

    fun getPlanById(id: String): Plan? {
        return planList.value?.find { it.id == id }
    }
}