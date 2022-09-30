package com.toppings.service

import com.toppings.dto.ToppingsDTO
import com.toppings.dto.StatisticDTO
import com.toppings.entity.Topping
import com.toppings.entity.Vote
import com.toppings.repository.VoteRepository
import org.springframework.stereotype.Service
import java.security.Principal
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
class ToppingService(val voteRepository: VoteRepository) {

    @Transactional
    fun saveOrUpdateFavorite(toppingsDTO: ToppingsDTO): ToppingsDTO {
        val toppings = toppingsDTO.toppings.mapTo(HashSet()) { Topping(it) }
        val vote = Vote(toppingsDTO.email)
        vote.toppings = toppings
        voteRepository.save(vote)
        return toppingsDTO
    }

    @Transactional
    fun getStatistic(): StatisticDTO {
        val votes = voteRepository.findAll()
        val statistic = mapToppingsToStatistic(votes)
        return StatisticDTO(statistic)
    }

    private fun mapToppingsToStatistic(votes: Iterable<Vote>): Map<String, Int> {
        return votes.flatMap { it.toppings }.groupingBy { it.name }.eachCount()
    }

    @Transactional
    fun getByEmail(principal: Principal?): ToppingsDTO {
        val email = principal?.name ?: throw EntityNotFoundException("You are not authorized")
        val vote = voteRepository.findVoteByEmail(email) ?: throw EntityNotFoundException("Vote for $email not found!")
        return ToppingsDTO(
            vote.email,
            vote.toppings.map { it.name }
        )
    }

}
