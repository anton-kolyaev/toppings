package com.toppings.repository

import com.toppings.entity.Vote
import org.springframework.data.repository.CrudRepository

interface VoteRepository: CrudRepository<Vote, String> {
    fun findVoteByEmail(email: String): Vote?
}
