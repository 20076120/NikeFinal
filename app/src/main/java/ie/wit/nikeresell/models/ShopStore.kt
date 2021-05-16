interface ShopStore {
    fun findAll() : List<ShopModel>
    fun findById(id: Long) : ShopModel?
    fun create(offer: ShopModel)
}