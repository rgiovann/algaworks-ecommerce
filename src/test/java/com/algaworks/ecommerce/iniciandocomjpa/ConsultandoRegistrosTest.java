package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class ConsultandoRegistrosTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void setUpBeforeAll(){

        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @BeforeEach
    public void setUp(){

        entityManager = entityManagerFactory.createEntityManager();

    }

    @AfterAll
    public static void tearDownAfterAll(){

            entityManagerFactory.close();
   }


    @AfterEach
    public void tearDown(){

            entityManager.close();

    }

    @Test
    public void buscarPorIdentificador()
    {
        Produto produto = entityManager.find(Produto.class,1);
        //Produto produto = entityManager.getReference(Produto.class,1);

        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Kindle", produto.getNome());


    }

    @Test
    public void atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);  // volta a instancia original no BD, faz nova consulta ao BD.

        Assertions.assertEquals("Kindle", produto.getNome());
    }

}
