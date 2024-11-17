package com.example.projectbackend;

import java.time.Duration;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import reactor.core.publisher.*;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@EnableCaching
public class SpringBootExampleApplication {

  public static void main(String[] args) {
    // SpringApplication.run(SpringBootExampleApplication.class, args);
    test();
  }

  @SneakyThrows
  private static void test() {
    System.out.println("start");
    // Flux.fromIterable(getList())
    // Mono.fromCallable(() -> getList())
    //     .flatMap(x -> Integer::parseInt)
    //     .subscribeOn(Schedulers.boundedElastic())
    //     .subscribe(System.out::println);
    // Flux.error(new RuntimeException()).doOnError((e) -> System.out.println("error"));
    // .subscribe(System.out::println);
    Flux.interval(Duration.ofMillis(100))
        .take(10)
        .doOnNext(System.out::println)
        .subscribeOn(Schedulers.parallel())
        .subscribe(System.out::println);
    System.out.println("end");
    Thread.sleep(5000);
  }

  @SneakyThrows
  private static List<String> getList() {
    Thread.sleep(3000);
    return List.of("01", "02", "03");
  }
}
