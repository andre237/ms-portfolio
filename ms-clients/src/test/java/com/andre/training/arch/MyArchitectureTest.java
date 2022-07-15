package com.andre.training.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@SuppressWarnings("unused")
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.andre.training")
public class MyArchitectureTest {

    @ArchTest
    public static final ArchRule infraAndCoreRule = noClasses()
            .that().resideInAPackage("..core..")
            .should().dependOnClassesThat().resideInAPackage("..infra..")
            .because("Business concepts must not depend on infrastructure details");

    @ArchTest
    public static final ArchRule applicationAndDomainRule = noClasses()
            .that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("..application..")
            .because("Domain classes must not depend on application use cases");

    @ArchTest
    public static final ArchRule useCaseNamingRule = classes()
            .that().haveNameMatching(".*UseCase")
            .should().resideInAnyPackage("..usecases..", "..application..");


}
