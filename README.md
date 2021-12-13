# debezium-fuse

Applicazione di esempio per il sink di messaggi proveniente da Kafka.

Questa applicazione è destinata al deploy su OpenShift attraverso il manifest presente in src/main/jkube, con l'utilizzo del plugin JKube stesso.

E' necessario intervenire nelle application.properties per modificare i parametri di connessione al database di destinazione

Allo stesso modo, nella classe Java della Rotta, vanno modificati i riferimenti ai topic Kafka.
