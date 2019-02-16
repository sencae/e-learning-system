--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

-- Started on 2019-01-31 12:14:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 197 (class 1259 OID 32823)
-- Name: id_increment; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_increment
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_increment OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 32820)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    id bigint DEFAULT nextval('public.id_increment'::regclass) NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    first_name character varying NOT NULL,
    last_name character varying NOT NULL,
    email character varying(255) NOT NULL,
    reg_id bigint NOT NULL
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 32836)
-- Name: user_groups_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_groups_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_groups_seq OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 32833)
-- Name: user_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_groups (
    id bigint DEFAULT nextval('public.user_groups_seq'::regclass) NOT NULL,
    group_name character varying NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE public.user_groups OWNER TO postgres;

--
-- TOC entry 2825 (class 0 OID 32820)
-- Dependencies: 196
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2827 (class 0 OID 32833)
-- Dependencies: 198
-- Data for Name: user_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_groups VALUES (1, 'admin', 'admin group');
INSERT INTO public.user_groups VALUES (2, 'user', 'simple user group');
INSERT INTO public.user_groups VALUES (3, 'unconfirmed user', 'group of unconfirmed users');


--
-- TOC entry 2834 (class 0 OID 0)
-- Dependencies: 197
-- Name: id_increment; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_increment', 1, true);


--
-- TOC entry 2835 (class 0 OID 0)
-- Dependencies: 199
-- Name: user_groups_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_groups_seq', 3, true);


--
-- TOC entry 2702 (class 2606 OID 41092)
-- Name: user_groups UG_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_groups
    ADD CONSTRAINT "UG_id" PRIMARY KEY (id);


--
-- TOC entry 2696 (class 2606 OID 41149)
-- Name: User email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT email UNIQUE (email);


--
-- TOC entry 2698 (class 2606 OID 41081)
-- Name: User id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 2700 (class 2606 OID 41052)
-- Name: User username; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT username UNIQUE (username);


--
-- TOC entry 2703 (class 2606 OID 41105)
-- Name: User for_UG; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "for_UG" FOREIGN KEY (reg_id) REFERENCES public.user_groups(id);


-- Completed on 2019-01-31 12:14:39

--
-- PostgreSQL database dump complete
--

