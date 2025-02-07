package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class JsonPlaceholderSimulation extends Simulation {
  
  val httpProtocol = http
    .baseUrl("https://jsonplaceholder.typicode.com")
    .acceptHeader("application/json")
    .userAgentHeader("Gatling Performance Test")

  val scn = scenario("JSONPlaceholder API Test")
    .exec(
      http("Get All Posts")
        .get("/posts")
        .check(status.is(200))
    )
    .pause(1)
    .exec(
      http("Get Single Post")
        .get("/posts/1")
        .check(status.is(200))
        .check(jsonPath("$.id").is("1"))
    )
    .pause(1)
    .exec(
      http("Get Post Comments")
        .get("/posts/1/comments")
        .check(status.is(200))
        .check(jsonPath("$[0].postId").is("1"))
    )
    .pause(1)
    .exec(
      http("Create New Post")
        .post("/posts")
        .header("Content-Type", "application/json")
        .body(StringBody("""{"title":"foo","body":"bar","userId":1}"""))
        .check(status.is(201))
        .check(jsonPath("$.title").is("foo"))
    )

  setUp(
    scn.inject(
      rampUsers(50).during(30.seconds),
      constantUsersPerSec(2).during(30.seconds)
    )
  ).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(5000),
      global.successfulRequests.percent.gt(95)
    )
}