
package com.aicon.biblioteca.repositorio;

import com.aicon.biblioteca.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface editorialRepo extends JpaRepository<Editorial, String> {
    
}
