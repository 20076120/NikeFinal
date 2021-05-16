import android.util.Log

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ShopMemStore : ShopStore {

    val offers = ArrayList<ShopModel>()

    override fun findAll(): List<ShopModel> {
        return offers
    }

    override fun findById(id:Long) : ShopModel? {
        val foundOffer: ShopModel? = offers.find { it.id == id }
        return foundOffer
    }

    override fun create(offer: ShopModel) {
        offer.id = getId()
        offers.add(offer)
        logAll()
    }

    fun logAll() {
        Log.v("Donate","** Donations List **")
        offers.forEach { Log.v("Donate","${it}") }
    }
}