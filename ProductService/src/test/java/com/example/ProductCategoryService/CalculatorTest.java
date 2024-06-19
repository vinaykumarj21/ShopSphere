package com.example.ProductCategoryService;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {
    @Test
    public void Test_AddTwo_Integers_ReturnsInteger(){
        //Arrange ( Object of a class we want unit test to execute on)
        Calculator calculator=new Calculator();
        //Act(calling function we want to test
        int result=calculator.add(1,3);
        //Assert(Comparing the Result
        assert(4==result);
    }

    //Test for Exception Handing
    @Test
    public void Test_DivideByZero_ThrowsException(){
        //Arrange
        Calculator calculator=new Calculator();
        //Act
        //int result=calculator.divide(1,0);
        //Assert
        assertThrows(ArithmeticException.class,()-> calculator.divide(1,0));

    }
}