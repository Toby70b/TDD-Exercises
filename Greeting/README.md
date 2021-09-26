# Greeting Kata TDD Excercise

This repository contains my solution for the [Greeting Kata Exercise](https://github.com/testdouble/contributing-tests/wiki/Greeting-Kata)

This exercise was fun and was a great way to get some hands-on with TDD after struggling with creating Unit tests after-the-fact for so long

As expected the code has 100% test coverage and every test runs successfully

## Requirements

While the requirements can be found in the link above here is a summarisation

* A method "greet()" should exist <br/>
* It should take an Array/List of Strings representing names to greet 
* It should return a String 
* If the parameter is null it should return "Hello, my friend"
* Given a single name it should output "Hello, \<name>."
* Given multiple names it should output each separated by a commad the the string should be closed with an oxford comman and "and" 
  * i.e. "Hello, Amy, Brian, and Charlotte." 
* Given a name that is entirely capatalised a "shout" should be returned
  * A shout is of the format "HELLO \<name>!"
* If both normal and shouted names are passed in the parameters the response should be split into two greetings first normal then shouted.
  * In this case the shout greeting should be prefixed with "AND"
* If a name contains a comma the name will be split with the comma acting as the delimiter
* Strings surrounded in escaped double quotes should be treated as a single name regardless of commas

## Notes
This was a great introduction to TDD and a fun, simple exercise. However as with all kata's/interview exercises there is
often ambiguity with some of the requirements. Of course as always further refactoring could be done but I time-boxed a short amount
of time for this exercise so I could continue onto others<br/>


For example in this exercise it wasnt 100% clear if, during a shout greeting, the penultimate name should be prefixed 
with the oxford comma. The requirement doesn't specify, thus my decision to add it, though since the start of the shout
greeting doesn't contain a comma I do wonder....
