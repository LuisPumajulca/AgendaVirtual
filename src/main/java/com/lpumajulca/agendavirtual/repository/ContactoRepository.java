package com.lpumajulca.agendavirtual.repository;

import com.lpumajulca.agendavirtual.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
}
