package com.project.ChatApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ChatApp.payloads.CategoryDto;
import com.project.ChatApp.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private transient CategoryController categoryController;

    @Mock
    private transient CategoryService categoryService;

    private static final String CATEGORY_ROOT = "/api/category";

    private static CategoryDto categoryDto;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        categoryDto = new CategoryDto();
        categoryDto.setDescription("more budget");
        categoryDto.setId(1L);
        categoryDto.setName("class a");
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(Collections.singletonList(new CategoryDto()));

        mockMvc.perform(get(CATEGORY_ROOT + "/getAll"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        when(categoryService.createCategory(any(CategoryDto.class))).thenReturn(categoryDto);

        mockMvc.perform(post(CATEGORY_ROOT + "/create")
                        .content(new ObjectMapper().writeValueAsString(categoryDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testGetUserById() throws Exception {
        when(categoryService.getCategoryById(anyLong())).thenReturn(categoryDto);

        mockMvc.perform(get(CATEGORY_ROOT + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        when(categoryService.updateCategory(any(CategoryDto.class), anyLong())).thenReturn(categoryDto);

        mockMvc.perform(put(CATEGORY_ROOT + "/update/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(categoryDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(categoryService).deleteCategory(anyLong());

        mockMvc.perform(delete(CATEGORY_ROOT + "/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}