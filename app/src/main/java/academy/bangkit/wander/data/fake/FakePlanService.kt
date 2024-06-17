package academy.bangkit.wander.data.fake

import academy.bangkit.wander.data.model.Plan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakePlanService {

    private val planList = MutableLiveData<List<Plan>>()

    init {
        planList.value = listOf(
            Plan(
                "1",
                "Bali 1",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "2",
                "Bali 2",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "3",
                "Bali 3",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "4",
                "Bali 4",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "5",
                "Bali 5",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
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