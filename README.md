Spring-Boot-Observability-Nexus
High-Scale Observability Framework for Distributed Systems. Implements Spring Boot 3.x and RabbitMQ to solve the "Asynchronous Visibility Gap." This project serves as a TPM R&D reference for LGTM Stack integration (Loki, Grafana, Tempo, Mimir) and OpenTelemetry standards.

Project: Observability-Nexus

Architect/Development: Naveen Komma

Project Status: Active Development | Phase 1 (Infrastructure & Telemetry Scaffolding)

🎯 Business Objective (The Problem Statement)
In high-traffic environments like Agoda, where thousands of microservices interact per second, "Blind Spots" in asynchronous communication are a major risk. Traditional monitoring often loses the thread when a request moves from a synchronous API into a message broker like RabbitMQ.

The Pain Points:
- The "Black Box" Problem: Inability to track a single traceId once a message enters a queue, leading to high MTTR (Mean Time To Resolution).

- Metric Silos: Infrastructure metrics (RabbitMQ CPU/Disk) and Application metrics (JVM/Request Latency) living in separate dashboards without correlation.

- Non-Standardized SLIs: Lack of a unified "Golden Signal" template for asynchronous producers and consumers.

✅ The Solution: The Observability Nexus
This project acts as a Standardized Telemetry Blueprint. By utilizing a "Sidecar-ready" architecture, it ensures that every message published to the event bus is automatically instrumented with Context Propagation. It bridges the gap between infrastructure health and application performance, providing a single pane of glass for SRE and Product teams.

🛠 Technology Stack & Frameworks

- Message Broker: RabbitMQ 3.12+ (AMQP 0-9-1) with Management Plugin & Native Prometheus Exporter.

- Backend Framework: Spring Boot 3.x (Spring AMQP + Actuator).

- Observability Stack (LGTM): * Prometheus: Time-series metrics storage.

- Grafana: Multi-source visualization & Alerting.

- Tempo: High-scale distributed tracing via OTLP.

- Instrumentation: Micrometer Tracing (Brave/OTel Bridge).

- Orchestration: Docker Desktop & Docker-Compose (Infrastructure-as-Code).

📊 TPM R&D & Strategy Focus:
This architecture is engineered to align with global site reliability and observability standards:

- Context Propagation: Preserving traceId across RabbitMQ headers to ensure end-to-end transaction visibility.
- Golden Signals Mapping: Specifically tracking Latency (time-in-queue), Traffic (msg/sec), Errors (DLX rates), and Saturation (queue depth).
- Dashboards-as-Code: Providing pre-configured Grafana JSON models for immediate service deployment.
- Anomaly Detection: R&D for identifying "Slow Consumers" using Prometheus histogram buckets ($le$).

🛡 Reliability & Observability Features:
- Distributed Tracing (Tempo): Visualizing the full lifecycle of a request from REST Controller $\rightarrow$ RabbitMQ $\rightarrow$ Consumer.
- Native Prometheus Scraping: Utilizing RabbitMQ 3.8+ native /metrics endpoint for zero-agent infrastructure monitoring.
- Micrometer Observation API: Using the latest Spring 6 Observation interface for unified logging, tracing, and metrics.
- Dead Letter Monitoring: Automated alerting on DLX (Dead Letter Exchange) activity to catch serialization or logic errors.
- Docker-Compose Orchestration: A repeatable, production-mirroring sandbox for local R&D and performance testing.


