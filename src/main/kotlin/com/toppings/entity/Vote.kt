package com.toppings.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class Vote(
    @Id val email: String
) {
    @ManyToMany(cascade = [CascadeType.ALL])
    var toppings: Set<Topping> = emptySet()
}
