import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShopModel(var id: Long = 0,
                         val paymentmethod: String = "N/A",
                         val amount: Int = 0) : Parcelable