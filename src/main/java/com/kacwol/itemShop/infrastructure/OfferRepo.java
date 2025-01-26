package com.kacwol.itemShop.infrastructure;

import com.kacwol.itemShop.domain.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {

    List<Offer> findAllByUserIdNot(Long userId);

    Optional<Offer> findByIdAndUserIdNot(Long id, Long userId);

    List<Offer> findAllByUserId(Long userId);
}
