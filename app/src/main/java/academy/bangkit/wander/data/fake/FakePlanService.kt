package academy.bangkit.wander.data.fake

import academy.bangkit.wander.data.model.Plan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakePlanService {

    private val planList = MutableLiveData<List<Plan>>()

    init {
        planList.value = listOf(
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
            Plan(
                "Bali",
                "Bali, Indonesia",
                "https://awsimages.detik.net.id/community/media/visual/2023/07/19/ilustrasi-pulau-bali_169.jpeg?w=600&q=90",
                "2021-10-10"
            ),
        )
    }

    fun getPlanList(): LiveData<List<Plan>> {
        return planList

    }
}