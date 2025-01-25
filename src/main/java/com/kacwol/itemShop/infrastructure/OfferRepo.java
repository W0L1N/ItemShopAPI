package com.kacwol.itemShop.infrastructure;

import com.kacwol.itemShop.domain.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
}
