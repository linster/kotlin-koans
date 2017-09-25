package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    //TODO probably not the most elegant.
    return customers
            .groupBy { it.orders.flatMap { it.products }.toSet() }
            .filterKeys { it.contains(product) }
            .values
            .fold(mutableSetOf<Customer>(), { set : MutableSet<Customer>, customerList : List<Customer> ->
                customerList.forEach({set.add(it)}) ; set
            })

}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
    return orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
    return customers.flatMap { it.orders }.flatMap { it.products }.count { it == product }
}
