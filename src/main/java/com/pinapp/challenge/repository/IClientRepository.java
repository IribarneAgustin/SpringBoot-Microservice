package com.pinapp.challenge.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pinapp.challenge.model.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client,Long>{

    @Query(value = "SELECT AVG(age) FROM client", nativeQuery = true)
    public abstract Double getAverageAge();
    
}
