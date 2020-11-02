package com.technicaltest.productservice.utils;

import com.technicaltest.productservice.BaseTest;
import com.technicaltest.productservice.entity.ProductEntity;
import com.technicaltest.productservice.repository.ProductRepository;
import com.technicaltest.productservice.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = Utils.class, controllers = Utils.class)
public class UtilsTest extends BaseTest {

    @Before
    public void init() {
    }

    @Test
    public void test_isEmpty_string_true() {
        // Given
        Boolean expected = true;
        String entry = "";
        // When
        Boolean actual = Utils.isEmpty(entry);
        // Then
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void test_isEmpty_string_false() {
        // Given
        Boolean expected = false;
        String entry = "Test";
        // When
        Boolean actual = Utils.isEmpty(entry);
        // Then
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void test_isEmpty_object_true() {
        // Given
        Boolean expected = true;
        Object entry = null;
        // When
        Boolean actual = Utils.isEmpty(entry);
        // Then
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void test_isEmpty_object_false() {
        // Given
        Boolean expected = false;
        Object entry = new ArrayList<>();
        // When
        Boolean actual = Utils.isEmpty(entry);
        // Then
        Assert.assertEquals(actual,expected);
    }
}