package com.toppings.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Topping (@Id val name: String)