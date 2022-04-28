package com.educ.services;

import com.educ.data.ReviewRepository;
import com.educ.entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("test du review service")
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModuleeService moduleeService;

    @BeforeAll
    static void initAll() {
        System.out.println("beforeAll");
    }

    @Test
    @DisplayName("creation review test")
    public void testCreationReview(){
        //create data
        int note=9;
        String comment="Tres bien";
        User user=this.userService.createUser("sasa","grgr", null,"","salsa@gmail.com","","",1L);
        Modulee modulee=moduleeService.createModule("Java", "", Level.ADVANCE, Language.EN,1L,"");
        Review review=this.reviewService.createReview(note, comment,1L,1L);
        List<Review> reviews=reviewService.findAll();
        assertNotNull(review);
        assertTrue(review.getNote()==9 && review.getComment().equals("Tres bien"));
        assertNotNull(reviews);
        assertEquals(reviews.size(),1);
    }

    @Test
    @DisplayName("update review test")
    public void testUpdateReview(){
        int note=9;
        String comment="Tres bien";
        Review review=new Review(note,comment);
        //Review review=this.reviewService.createReview(note, comment);
        List<Review> reviews=reviewService.findAll();
        this.reviewService.updateReview(1L,4,"Insuffisant");
        List<Review> reviewsUpdated=this.reviewService.findAll();
        assertEquals(reviews.size(),reviewsUpdated.size());
        assertTrue((reviewsUpdated.get(0).getNote()==4) && (reviewsUpdated.get(0).getComment().equals("Insuffisant")));
        this.reviewService.updateReview(9L,4,"Insuffisant");
        reviews=this.reviewService.findAll();
        assertTrue(reviewsUpdated.size()==1 && reviewsUpdated.get(0).getId()!=9L);
    }


    @Test
    @DisplayName("delete review test")
    public void testDeleteReview(){
        int note=9;
        String comment="Tres bien";
        Review review=new Review(note,comment);
        //Review review=this.reviewService.createReview(note, comment);
        List<Review> reviews=reviewService.findAll();
        assertNotNull(review);
        assertEquals(reviews.size(),1);
        this.reviewService.deleteReview(1L);
        reviews=reviewService.findAll();
        assertEquals(reviews.size(),0);
        assertFalse(reviews.contains(review));
        review=new Review(5,"Moyen");
        //review=this.reviewService.createReview(5,"Moyen");
        reviews=this.reviewService.findAll();
        this.reviewService.deleteReview(10L);
        reviews=this.reviewService.findAll();
        assertTrue(reviews.contains(review));
    }
}
