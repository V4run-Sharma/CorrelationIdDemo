# Correlation ID Demo Application

Demo Spring Boot application showcasing usage of the
**spring-boot-starter-correlation-id** for end-to-end request correlation.

This application demonstrates how correlation IDs are automatically:

* Generated for incoming requests
* Added to logs via MDC
* Propagated across RestTemplate calls
* Propagated across WebClient calls
* Preserved across async execution

---

## Purpose

This demo application exists to visually and practically demonstrate the
capabilities of the correlation ID starter in a real Spring Boot application.

It is intended for:

* Portfolio demonstration
* Local testing
* Understanding end-to-end correlation behavior
* Verifying async MDC propagation

---

## Uses

This application uses the custom Spring Boot starter:

[GitHub](https://github.com/V4run-Sharma/spring-boot-starter-correlation-id)

---

## Features Demonstrated

This demo proves the following behaviors:

* Incoming HTTP request correlation
* Automatic correlation ID generation
* Custom header name support
* RestTemplate propagation
* WebClient propagation
* Async MDC propagation using @Async

---

## Setup

### Prerequisites

* Java 17+
* Maven 3.8+
* Local install of the starter

---

## Install Starter Locally

From the starter repo, run:

```
cd spring-boot-starter-correlation-id
mvn clean install
```

This installs the starter into your local Maven repository.

---

## Run the Demo App

From this repo, run:

```
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8090
```

---

## Logging Configuration

This demo uses a logging pattern that includes the correlation ID:

```
logging.pattern.console=%d{HH:mm:ss.SSS} [%X{correlationId}] %-5level %logger - %msg%n
```

This makes it easy to visually verify correlation across logs.

---

## Demo Endpoints

### Main Demo Endpoint

```
GET /demo/sync
```

This endpoint triggers:

* A RestTemplate call to /demo/downstream
* A WebClient call to /demo/downstream
* An async method using @Async

---

### Downstream Endpoint

```
GET /demo/downstream
```

Simulates a downstream service for propagation testing.

---

## Example Request

```
curl http://localhost:8090/demo/sync
```

---

## Example Response

```
sync-ok | rest=downstream-ok | webclient=downstream-ok
```

---

## Example Logs

All log lines will share the same correlation ID:

```
[abc-123] Handling /sync request
[abc-123] Handling /downstream request
[abc-123] Handling /downstream request
[abc-123] Running async task
[abc-123] Async task completed
```

This proves end-to-end correlation across:

* Incoming request
* Outgoing HTTP calls
* Async execution

---

## Custom Header Demo

You can override the correlation header:

```
correlation-id.header-name=X-Request-Id
```

The response will then include:

```
X-Request-Id: <uuid>
```

---

## Why This Matters

This demo shows how a lightweight Spring Boot starter can provide:

* Distributed request correlation
* Log-based tracing
* Async-safe MDC propagation

Without requiring a full distributed tracing stack.

---

## License

Apache License 2.0

---

## Related Project

Starter Library:
[GitHub](https://github.com/V4run-Sharma/spring-boot-starter-correlation-id)
