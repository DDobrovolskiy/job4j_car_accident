package ru.job4j.begin;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BeginTest {

    @Test
    public void getString() {
        Assert.assertThat("String", is(new Begin().getString()));
    }
}