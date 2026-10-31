
\restrict 6i3hA47GD8XnG9sPaiXmvJma3BJcPwaRWcxLQtLlEbxfdkS2CQPgJfJFSJHWcEk


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE public.print_operator (
    id uuid NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    phone character varying(255),
    printer_id uuid
);



CREATE TABLE public.print_operator_print_service (
    print_operator_id uuid NOT NULL,
    print_service_id uuid NOT NULL
);



CREATE TABLE public.print_request (
    id uuid NOT NULL,
    copies integer NOT NULL,
    document_name character varying(255),
    request_date date,
    status character varying(255),
    print_operator_id uuid,
    student_id uuid,
    CONSTRAINT print_request_status_check CHECK (((status)::text = ANY ((ARRAY['SUBMITTED'::character varying, 'PROCESSING'::character varying, 'READY'::character varying, 'CANCELLED'::character varying])::text[])))
);



CREATE TABLE public.print_service (
    id uuid NOT NULL,
    name character varying(255)
);



CREATE TABLE public.printer (
    id uuid NOT NULL,
    name character varying(255),
    printer_number integer NOT NULL
);



CREATE TABLE public.student (
    id uuid NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    reg_number character varying(255)
);



COPY public.print_operator (id, first_name, last_name, phone, printer_id) FROM stdin;
e8db9e84-4bed-4b08-b830-6ca6cc89ea54	Eric	Operator	0780000002	c94da6ab-fb83-4390-b431-4a6be3966a4f
4e520f33-5981-464c-9f0e-f789fe49228f	Grace	Operator	0780000003	1ef3c121-1222-406f-801f-5c9a855dd1d9
4d7f8cdd-70ec-406d-a68d-c838adc87be9	Alice	Operator	0780000001	e5e1e90d-3b79-4642-b577-d90a8c3b57c0
\.



COPY public.print_operator_print_service (print_operator_id, print_service_id) FROM stdin;
e8db9e84-4bed-4b08-b830-6ca6cc89ea54	92e6fc1b-eb1e-4da7-a406-60eb028dc649
4e520f33-5981-464c-9f0e-f789fe49228f	179849ed-f325-484a-b4c6-98edf22eccb3
4e520f33-5981-464c-9f0e-f789fe49228f	92e6fc1b-eb1e-4da7-a406-60eb028dc649
4d7f8cdd-70ec-406d-a68d-c838adc87be9	179849ed-f325-484a-b4c6-98edf22eccb3
4d7f8cdd-70ec-406d-a68d-c838adc87be9	e76989e9-68d4-49ec-8261-43b0b8f7d354
\.



COPY public.print_request (id, copies, document_name, request_date, status, print_operator_id, student_id) FROM stdin;
907d5fac-1d71-4cb5-be33-65e553452620	1	Document 1	2026-10-01	READY	4d7f8cdd-70ec-406d-a68d-c838adc87be9	9b769f19-fe20-4ad8-87ee-f41154d174be
6e867079-87b0-47c1-adc3-78efd287499c	2	Document 2	2026-10-20	PROCESSING	4d7f8cdd-70ec-406d-a68d-c838adc87be9	9b769f19-fe20-4ad8-87ee-f41154d174be
4625b804-6ff4-46fd-93fe-966a5b40b841	3	Document 3	2026-10-20	CANCELLED	4d7f8cdd-70ec-406d-a68d-c838adc87be9	9b769f19-fe20-4ad8-87ee-f41154d174be
e2d4b1c7-550d-47b1-8dd4-3c2a22c15293	1	Document 4	2026-10-25	READY	e8db9e84-4bed-4b08-b830-6ca6cc89ea54	3b7b0fce-b74c-40df-ac8e-84d4e698c9b7
7dcef87a-2f39-4cd7-b216-03d453440acb	2	Document 5	2026-10-31	SUBMITTED	4e520f33-5981-464c-9f0e-f789fe49228f	3b7b0fce-b74c-40df-ac8e-84d4e698c9b7
4c16281f-f08f-458f-8ed5-25cb935c411d	3	Document 6	2026-11-01	SUBMITTED	4d7f8cdd-70ec-406d-a68d-c838adc87be9	2092bacf-ab92-46c6-bb14-cc81e848c419
246ff1d1-6c0e-4f2c-95dc-b1aca969812e	1	Document 7	2026-11-05	PROCESSING	e8db9e84-4bed-4b08-b830-6ca6cc89ea54	e5ee7692-cdef-4e0e-9c4b-2d5803d3780a
29d54425-9a4e-46df-94a2-95f6a6a3ef15	2	Document 8	2026-11-10	CANCELLED	4e520f33-5981-464c-9f0e-f789fe49228f	405eb5e3-2261-4a46-b29a-d01236cd4cbb
051b2f10-3a60-417c-8622-339775a51ae6	3	Document 9	2026-11-15	READY	e8db9e84-4bed-4b08-b830-6ca6cc89ea54	2092bacf-ab92-46c6-bb14-cc81e848c419
b4bb574a-f3c8-4134-83a1-097bd3575633	1	Document 10	2026-11-20	SUBMITTED	4e520f33-5981-464c-9f0e-f789fe49228f	e5ee7692-cdef-4e0e-9c4b-2d5803d3780a
\.



COPY public.print_service (id, name) FROM stdin;
179849ed-f325-484a-b4c6-98edf22eccb3	Black and White
92e6fc1b-eb1e-4da7-a406-60eb028dc649	Colour Printing
e76989e9-68d4-49ec-8261-43b0b8f7d354	Binding
1e9b39cf-a5de-4957-8f46-e601e62a71a1	Lamination
\.



COPY public.printer (id, name, printer_number) FROM stdin;
e5e1e90d-3b79-4642-b577-d90a8c3b57c0	Printer 1	1
c94da6ab-fb83-4390-b431-4a6be3966a4f	Printer 2	2
1ef3c121-1222-406f-801f-5c9a855dd1d9	Printer 3	3
1b3adf1c-2d82-4738-afaa-399a9b65454f	Printer 4	4
\.



COPY public.student (id, email, first_name, last_name, reg_number) FROM stdin;
9b769f19-fe20-4ad8-87ee-f41154d174be	nelly@example.com	Nelly	Isange	27818
3b7b0fce-b74c-40df-ac8e-84d4e698c9b7	alice@example.com	Alice	Isange	27819
2092bacf-ab92-46c6-bb14-cc81e848c419	eric@example.com	Eric	Mugisha	27820
e5ee7692-cdef-4e0e-9c4b-2d5803d3780a	grace@example.com	Grace	Uwase	27821
405eb5e3-2261-4a46-b29a-d01236cd4cbb	jean@example.com	Jean	Ishimwe	27822
\.



ALTER TABLE ONLY public.print_operator
    ADD CONSTRAINT print_operator_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.print_request
    ADD CONSTRAINT print_request_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.print_service
    ADD CONSTRAINT print_service_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.printer
    ADD CONSTRAINT printer_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.student
    ADD CONSTRAINT ukaawmi7s2h0jubly89g4yjn39g UNIQUE (reg_number);



ALTER TABLE ONLY public.print_request
    ADD CONSTRAINT fk8dth9iy4xc813h4yku4i97hb7 FOREIGN KEY (student_id) REFERENCES public.student(id);



ALTER TABLE ONLY public.print_request
    ADD CONSTRAINT fkbd8sptbie0uhw427ecsnjaavc FOREIGN KEY (print_operator_id) REFERENCES public.print_operator(id);



ALTER TABLE ONLY public.print_operator_print_service
    ADD CONSTRAINT fki7kxjfho5t7quenj9tmqywebt FOREIGN KEY (print_operator_id) REFERENCES public.print_operator(id);



ALTER TABLE ONLY public.print_operator_print_service
    ADD CONSTRAINT fkj0bud8bm4qps6sankiakg4wlm FOREIGN KEY (print_service_id) REFERENCES public.print_service(id);



ALTER TABLE ONLY public.print_operator
    ADD CONSTRAINT fkrixako95135t77xskr6b7lbo2 FOREIGN KEY (printer_id) REFERENCES public.printer(id);



\unrestrict 6i3hA47GD8XnG9sPaiXmvJma3BJcPwaRWcxLQtLlEbxfdkS2CQPgJfJFSJHWcEk
