package academy.bangkit.wander.presentation.saved.widgets

data class Place(
    val id: Int,
    val name: String,
    val location: String,
    val imageResource: Int, // Resource ID for the image
    val description: String,
    val moreImages: List<Int>
)
