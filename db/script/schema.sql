
--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

-- Started on 2019-05-03 21:27:04

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
-- TOC entry 224 (class 1255 OID 65833)
-- Name: create_user_function(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.create_user_function() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ 
BEGIN
	INSERT INTO user_info(user_id) VALUES (new.ID);
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.create_user_function() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 221 (class 1259 OID 65926)
-- Name: answers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answers (
    id bigint NOT NULL,
    answer character varying(255) NOT NULL,
    parent_question bigint,
    correct_answer boolean DEFAULT false NOT NULL
);


ALTER TABLE public.answers OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 65924)
-- Name: answers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.answers_id_seq OWNER TO postgres;

--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 220
-- Name: answers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.answers_id_seq OWNED BY public.answers.id;


--
-- TOC entry 201 (class 1259 OID 49344)
-- Name: confirmation_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.confirmation_token (
    token_id integer NOT NULL,
    confirmation_token character varying(255),
    created_date timestamp without time zone,
    user_id bigint
);


ALTER TABLE public.confirmation_token OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 49342)
-- Name: confirmation_token_token_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.confirmation_token_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.confirmation_token_token_id_seq OWNER TO postgres;

--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 200
-- Name: confirmation_token_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.confirmation_token_token_id_seq OWNED BY public.confirmation_token.token_id;


--
-- TOC entry 208 (class 1259 OID 57575)
-- Name: course_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course_resources (
    id integer NOT NULL,
    url character varying(255),
    title character varying(255),
    topic_id bigint NOT NULL
);


ALTER TABLE public.course_resources OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 57573)
-- Name: course_resources_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.course_resources_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.course_resources_id_seq OWNER TO postgres;

--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 207
-- Name: course_resources_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.course_resources_id_seq OWNED BY public.course_resources.id;


--
-- TOC entry 204 (class 1259 OID 57550)
-- Name: course_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course_type (
    id integer NOT NULL,
    type_name character varying(255)
);


ALTER TABLE public.course_type OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 57570)
-- Name: course_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.course_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.course_type_id_seq OWNER TO postgres;

--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 206
-- Name: course_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.course_type_id_seq OWNED BY public.course_type.id;


--
-- TOC entry 202 (class 1259 OID 57534)
-- Name: courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.courses (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    description character varying(1000),
    professor_id bigint NOT NULL,
    start_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    img_url character varying(255)
);


ALTER TABLE public.courses OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 57547)
-- Name: courses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.courses_id_seq OWNER TO postgres;

--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 203
-- Name: courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id;


--
-- TOC entry 196 (class 1259 OID 32823)
-- Name: id_increment; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_increment
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_increment OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 65906)
-- Name: questions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questions (
    id bigint NOT NULL,
    question character varying(2000) NOT NULL,
    parent_test bigint,
    checkbox_type boolean DEFAULT false NOT NULL
);


ALTER TABLE public.questions OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 65904)
-- Name: questions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.questions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.questions_id_seq OWNER TO postgres;

--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 218
-- Name: questions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.questions_id_seq OWNED BY public.questions.id;


--
-- TOC entry 209 (class 1259 OID 57586)
-- Name: resources_of_course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.resources_of_course (
    resource_id integer,
    course_id integer,
    id integer NOT NULL
);


ALTER TABLE public.resources_of_course OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 65835)
-- Name: resources_of_course_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.resources_of_course_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.resources_of_course_id_seq OWNER TO postgres;

--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 211
-- Name: resources_of_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.resources_of_course_id_seq OWNED BY public.resources_of_course.id;


--
-- TOC entry 223 (class 1259 OID 65940)
-- Name: test_results; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_results (
    id bigint NOT NULL,
    user_id bigint,
    date timestamp without time zone,
    result smallint,
    test_id bigint
);


ALTER TABLE public.test_results OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 65938)
-- Name: test_results_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.test_results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.test_results_id_seq OWNER TO postgres;

--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 222
-- Name: test_results_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.test_results_id_seq OWNED BY public.test_results.id;


--
-- TOC entry 217 (class 1259 OID 65897)
-- Name: tests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tests (
    id bigint NOT NULL,
    test_name character varying(255)
);


ALTER TABLE public.tests OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 65895)
-- Name: tests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tests_id_seq OWNER TO postgres;

--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 216
-- Name: tests_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tests_id_seq OWNED BY public.tests.id;


--
-- TOC entry 213 (class 1259 OID 65856)
-- Name: topics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topics (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    course_id bigint
);


ALTER TABLE public.topics OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 65854)
-- Name: topics_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topics_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topics_id_seq OWNER TO postgres;

--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 212
-- Name: topics_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topics_id_seq OWNED BY public.topics.id;


--
-- TOC entry 205 (class 1259 OID 57557)
-- Name: type_of_courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_of_courses (
    type_id integer,
    course_id integer
);


ALTER TABLE public.type_of_courses OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 41150)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id bigint DEFAULT nextval('public.id_increment'::regclass) NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    reg_id bigint NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 32836)
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
-- TOC entry 197 (class 1259 OID 32833)
-- Name: user_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_groups (
    id bigint DEFAULT nextval('public.user_groups_seq'::regclass) NOT NULL,
    group_name character varying NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE public.user_groups OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 57627)
-- Name: user_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_info (
    user_id bigint NOT NULL,
    avatar_url character varying(255),
    university character varying(255),
    brief_information character varying(1000)
);


ALTER TABLE public.user_info OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 65876)
-- Name: users_on_courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_on_courses (
    id bigint NOT NULL,
    user_id bigint,
    course_id bigint
);


ALTER TABLE public.users_on_courses OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 65874)
-- Name: users_on_courses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_on_courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_on_courses_id_seq OWNER TO postgres;

--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 214
-- Name: users_on_courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_on_courses_id_seq OWNED BY public.users_on_courses.id;


--
-- TOC entry 2786 (class 2604 OID 65929)
-- Name: answers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers ALTER COLUMN id SET DEFAULT nextval('public.answers_id_seq'::regclass);


--
-- TOC entry 2774 (class 2604 OID 49347)
-- Name: confirmation_token token_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token ALTER COLUMN token_id SET DEFAULT nextval('public.confirmation_token_token_id_seq'::regclass);


--
-- TOC entry 2779 (class 2604 OID 57578)
-- Name: course_resources id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_resources ALTER COLUMN id SET DEFAULT nextval('public.course_resources_id_seq'::regclass);


--
-- TOC entry 2778 (class 2604 OID 57572)
-- Name: course_type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_type ALTER COLUMN id SET DEFAULT nextval('public.course_type_id_seq'::regclass);


--
-- TOC entry 2775 (class 2604 OID 57549)
-- Name: courses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);


--
-- TOC entry 2784 (class 2604 OID 65909)
-- Name: questions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questions ALTER COLUMN id SET DEFAULT nextval('public.questions_id_seq'::regclass);


--
-- TOC entry 2780 (class 2604 OID 65837)
-- Name: resources_of_course id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course ALTER COLUMN id SET DEFAULT nextval('public.resources_of_course_id_seq'::regclass);


--
-- TOC entry 2788 (class 2604 OID 65943)
-- Name: test_results id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_results ALTER COLUMN id SET DEFAULT nextval('public.test_results_id_seq'::regclass);


--
-- TOC entry 2783 (class 2604 OID 65900)
-- Name: tests id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tests ALTER COLUMN id SET DEFAULT nextval('public.tests_id_seq'::regclass);


--
-- TOC entry 2781 (class 2604 OID 65859)
-- Name: topics id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topics ALTER COLUMN id SET DEFAULT nextval('public.topics_id_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 65879)
-- Name: users_on_courses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_on_courses ALTER COLUMN id SET DEFAULT nextval('public.users_on_courses_id_seq'::regclass);


--
-- TOC entry 2993 (class 0 OID 65926)
-- Dependencies: 221
-- Data for Name: answers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.answers VALUES (50, 'All of the above', 23, true);
INSERT INTO public.answers VALUES (51, 'It is cheaper to put them in space', 24, false);
INSERT INTO public.answers VALUES (52, 'You can only see visible light from space', 24, false);
INSERT INTO public.answers VALUES (54, 'They are closer to what they are studying', 24, false);
INSERT INTO public.answers VALUES (53, 'The atmosphere blocks certain EM waves', 24, true);
INSERT INTO public.answers VALUES (55, 'It makes them bigger than the other planets', 25, false);
INSERT INTO public.answers VALUES (56, 'It makes them orbit in the same direction', 25, false);
INSERT INTO public.answers VALUES (58, 'It means they will all spiral into the sun in 2 million years', 25, false);
INSERT INTO public.answers VALUES (57, 'It made them consist primarily of rock', 25, true);
INSERT INTO public.answers VALUES (60, 'Supernova and neutron star', 26, false);
INSERT INTO public.answers VALUES (61, 'Neutron star and black hole', 26, false);
INSERT INTO public.answers VALUES (62, 'Supergiant star and neutron star', 26, false);
INSERT INTO public.answers VALUES (59, 'Planetary nebula and white dwarf', 26, true);
INSERT INTO public.answers VALUES (6, 'answer#21', 2, true);
INSERT INTO public.answers VALUES (9, 'jjj', 13, false);
INSERT INTO public.answers VALUES (10, 'kkk', 13, true);
INSERT INTO public.answers VALUES (3, 'answer#2', 1, true);
INSERT INTO public.answers VALUES (1, 'answer#1', 1, false);
INSERT INTO public.answers VALUES (15, 'High heat and low pressure', 15, false);
INSERT INTO public.answers VALUES (17, 'Hydrogen and helium
', 15, false);
INSERT INTO public.answers VALUES (18, 'Uranium and plutonium', 15, false);
INSERT INTO public.answers VALUES (16, 'High heat and high pressure', 15, true);
INSERT INTO public.answers VALUES (19, 'White dwarf and black dwarf', 16, false);
INSERT INTO public.answers VALUES (21, 'Red giant and red supergiant', 16, false);
INSERT INTO public.answers VALUES (22, 'black hole and black dwarf', 16, false);
INSERT INTO public.answers VALUES (20, 'Neutron star and black hole', 16, true);
INSERT INTO public.answers VALUES (23, 'Red', 17, false);
INSERT INTO public.answers VALUES (24, 'White', 17, false);
INSERT INTO public.answers VALUES (26, 'Yellow', 17, false);
INSERT INTO public.answers VALUES (25, 'Blue', 17, true);
INSERT INTO public.answers VALUES (27, 'When it runs out of helium to fuse', 18, false);
INSERT INTO public.answers VALUES (28, 'When it turns into a black dwarf', 18, false);
INSERT INTO public.answers VALUES (29, 'When it turns into a planetary nebula', 18, false);
INSERT INTO public.answers VALUES (30, 'When it runs out of hydrogen to fuse', 18, true);
INSERT INTO public.answers VALUES (32, 'Gravity and mass', 19, false);
INSERT INTO public.answers VALUES (33, 'Stars are unstable and not at equilibrium', 19, false);
INSERT INTO public.answers VALUES (34, 'Nuclear fusion and magnetism', 19, false);
INSERT INTO public.answers VALUES (31, 'Gravity and pressure from nuclear fusion', 19, true);
INSERT INTO public.answers VALUES (35, 'Mass





', 20, false);
INSERT INTO public.answers VALUES (36, 'Speed of light', 20, false);
INSERT INTO public.answers VALUES (38, 'Wavelength', 20, false);
INSERT INTO public.answers VALUES (37, 'Energy', 20, true);
INSERT INTO public.answers VALUES (39, 'The sun''s existence





', 21, false);
INSERT INTO public.answers VALUES (41, 'Life on this planet', 21, false);
INSERT INTO public.answers VALUES (42, 'Most objects in the universe are coming towards us', 21, false);
INSERT INTO public.answers VALUES (40, 'Cosmic background radiation', 21, true);
INSERT INTO public.answers VALUES (43, 'Hydrogen and helium', 22, false);
INSERT INTO public.answers VALUES (45, 'Uranium and plutonium', 22, false);
INSERT INTO public.answers VALUES (46, 'Pressure and water', 22, false);
INSERT INTO public.answers VALUES (44, 'High heat and force', 22, true);
INSERT INTO public.answers VALUES (47, 'Means an object is moving away from us', 23, false);
INSERT INTO public.answers VALUES (48, 'Means the objects light has had its frequency lowered', 23, false);
INSERT INTO public.answers VALUES (49, 'It is evidence for an expanding universe', 23, false);


--
-- TOC entry 2973 (class 0 OID 49344)
-- Dependencies: 201
-- Data for Name: confirmation_token; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2980 (class 0 OID 57575)
-- Dependencies: 208
-- Data for Name: course_resources; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.course_resources VALUES (65, 'https://drive.google.com/uc?id=15_UBze9CZc5hRiHfqvJUfEyrCNwb72FE&export=download', 'hintersee-3601004_960_720.jpg', 1);


--
-- TOC entry 2976 (class 0 OID 57550)
-- Dependencies: 204
-- Data for Name: course_type; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2974 (class 0 OID 57534)
-- Dependencies: 202
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.courses VALUES (15, 'Digital devices and microprocessors', 'The course “Digital devices and microprocessors” introduces students to the modern element base of digital devices, and is devoted to the study of obtaining knowledge about the general principles of building digital systems and organizing their programmed control, methods and techniques of programming microcontrollers. The course "Digital devices and microprocessors" includes theoretical and practical parts.', 46, '2019-04-24 19:59:00', '2019-04-30 19:59:00', NULL);
INSERT INTO public.courses VALUES (1, 'Basic Astronomy', 'The purpose of this course of lectures is to introduce students to the basic concepts of astronomy, its main achievements and modern problems.
It will focus on the most important concepts of astronomy and the features of the work of astronomers, their instruments and objects of study: about what can be seen in the telescope - planets, stars, galaxies; and that which is not visible - dark matter and dark energy.', 46, '2019-04-02 19:59:00', '2019-04-02 19:59:00', 'https://drive.google.com/uc?id=1379BgS8hRO1IShvwj6wO14HkIYB1goiG&export=download');


--
-- TOC entry 2991 (class 0 OID 65906)
-- Dependencies: 219
-- Data for Name: questions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.questions VALUES (2, 'question#2', 15, false);
INSERT INTO public.questions VALUES (1, 'question', 15, false);
INSERT INTO public.questions VALUES (13, 'question#3', 15, false);
INSERT INTO public.questions VALUES (15, 'What two things are needed to start nuclear fusion?', 1, false);
INSERT INTO public.questions VALUES (16, 'What two stellar objects can arise from a supernova?', 1, false);
INSERT INTO public.questions VALUES (17, 'What color are the hottest stars?', 1, false);
INSERT INTO public.questions VALUES (18, 'When does a star leave the main sequence?', 1, false);
INSERT INTO public.questions VALUES (19, 'What competing forces keep main sequence stars at equilibrium?', 1, false);
INSERT INTO public.questions VALUES (21, 'What evidence do we have of the inflation right after the Big Bang?', 1, false);
INSERT INTO public.questions VALUES (22, 'What two things are required for nuclear fusion?', 1, false);
INSERT INTO public.questions VALUES (23, 'What is a redshift?', 1, false);
INSERT INTO public.questions VALUES (24, 'Why are certain telescopes in space?', 1, false);
INSERT INTO public.questions VALUES (25, 'What does being close to the sun mean to the inner planets?', 1, false);
INSERT INTO public.questions VALUES (26, 'What two steller objects come after a red giant in the life cycle of stars?', 1, false);
INSERT INTO public.questions VALUES (20, 'In the equation E=mc^2, what does the E stand for?', 1, false);


--
-- TOC entry 2981 (class 0 OID 57586)
-- Dependencies: 209
-- Data for Name: resources_of_course; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2995 (class 0 OID 65940)
-- Dependencies: 223
-- Data for Name: test_results; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.test_results VALUES (2, 49, '2019-05-03 18:08:46.947', 66, 15);


--
-- TOC entry 2989 (class 0 OID 65897)
-- Dependencies: 217
-- Data for Name: tests; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tests VALUES (15, 'test#1');
INSERT INTO public.tests VALUES (1, 'Final test');


--
-- TOC entry 2985 (class 0 OID 65856)
-- Dependencies: 213
-- Data for Name: topics; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.topics VALUES (1, 'Lecture', 15);
INSERT INTO public.topics VALUES (0, 'Practice', 15);
INSERT INTO public.topics VALUES (12, 'ggg', 15);


--
-- TOC entry 2977 (class 0 OID 57557)
-- Dependencies: 205
-- Data for Name: type_of_courses; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2971 (class 0 OID 41150)
-- Dependencies: 199
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" VALUES (49, 'votteban@mail.ru', 'Jhon', 'Doe', '$2a$10$1dGCPa6SLzvZQuL.0M4rY./Ah2kKHFUs3CiPEEPWIIAzl3cWWWWxy', 'student', 5);
INSERT INTO public."user" VALUES (46, 'xarder1@gmail.com', 'Mark', 'Krinsberg', '$2a$10$HoFTawB5fZ5gtAFnHtCYJO3S7YEMQdNtR3l/ttYfVXxXKYaJzV/Ei', 'prof', 4);


--
-- TOC entry 2969 (class 0 OID 32833)
-- Dependencies: 197
-- Data for Name: user_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_groups VALUES (1, 'admin', 'admin group');
INSERT INTO public.user_groups VALUES (2, 'user', 'simple user group');
INSERT INTO public.user_groups VALUES (3, 'unconfirmed user', 'group of unconfirmed users');
INSERT INTO public.user_groups VALUES (4, 'professor', 'group of professors');
INSERT INTO public.user_groups VALUES (5, 'student', 'group of students');


--
-- TOC entry 2982 (class 0 OID 57627)
-- Dependencies: 210
-- Data for Name: user_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_info VALUES (49, NULL, NULL, NULL);
INSERT INTO public.user_info VALUES (46, 'https://drive.google.com/uc?id=1B9UIv5RbyzpeAcmY7BRQRGEq01SmkeOM&export=download', 'Voronezh State University', 'The world and its environment has been going through an enormous upheaval particularly in the last few decades. We need to develop thinkers who will be creative, engaged, skilled, informed and thoughtful about the past, present and future interactions of people with each other and their environment.

Previously I''ve worked in a law firm and travelled the world,  but the job which gives me the the most fulfilment is... teaching. It''s a privilege to be a part of this school community and watch those in our care blossom into maturity..

That''s the picture of my (always messy) desk in Society & Environment office, where you can find me or call x226');


--
-- TOC entry 2987 (class 0 OID 65876)
-- Dependencies: 215
-- Data for Name: users_on_courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users_on_courses VALUES (4, 49, 15);
INSERT INTO public.users_on_courses VALUES (5, 49, 1);


--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 220
-- Name: answers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answers_id_seq', 62, true);


--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 200
-- Name: confirmation_token_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.confirmation_token_token_id_seq', 31, true);


--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 207
-- Name: course_resources_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.course_resources_id_seq', 65, true);


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 206
-- Name: course_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.course_type_id_seq', 1, false);


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 203
-- Name: courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.courses_id_seq', 17, true);


--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 196
-- Name: id_increment; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_increment', 50, true);


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 218
-- Name: questions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.questions_id_seq', 26, true);


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 211
-- Name: resources_of_course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.resources_of_course_id_seq', 8, true);


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 222
-- Name: test_results_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_results_id_seq', 2, true);


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 216
-- Name: tests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tests_id_seq', 2, true);


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 212
-- Name: topics_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.topics_id_seq', 12, true);


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 198
-- Name: user_groups_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_groups_seq', 5, true);


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 214
-- Name: users_on_courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_on_courses_id_seq', 5, true);


--
-- TOC entry 2790 (class 2606 OID 41092)
-- Name: user_groups UG_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_groups
    ADD CONSTRAINT "UG_id" PRIMARY KEY (id);


--
-- TOC entry 2825 (class 2606 OID 65931)
-- Name: answers answers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_pkey PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 49349)
-- Name: confirmation_token confirmation_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_pkey PRIMARY KEY (token_id);


--
-- TOC entry 2803 (class 2606 OID 57583)
-- Name: course_resources course_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_resources
    ADD CONSTRAINT course_resources_pkey PRIMARY KEY (id);


--
-- TOC entry 2799 (class 2606 OID 57554)
-- Name: course_type course_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_type
    ADD CONSTRAINT course_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2796 (class 2606 OID 57541)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);


--
-- TOC entry 2822 (class 2606 OID 65911)
-- Name: questions questions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questions
    ADD CONSTRAINT questions_pkey PRIMARY KEY (id);


--
-- TOC entry 2807 (class 2606 OID 65843)
-- Name: resources_of_course resources_of_course_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course
    ADD CONSTRAINT resources_of_course_pk PRIMARY KEY (id);


--
-- TOC entry 2828 (class 2606 OID 65945)
-- Name: test_results test_results_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_results
    ADD CONSTRAINT test_results_pkey PRIMARY KEY (id);


--
-- TOC entry 2819 (class 2606 OID 65902)
-- Name: tests tests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT tests_pkey PRIMARY KEY (id);


--
-- TOC entry 2813 (class 2606 OID 65861)
-- Name: topics topics_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topics
    ADD CONSTRAINT topics_pkey PRIMARY KEY (id);


--
-- TOC entry 2810 (class 2606 OID 57634)
-- Name: user_info user_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_info
    ADD CONSTRAINT user_info_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2792 (class 2606 OID 41157)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2816 (class 2606 OID 65881)
-- Name: users_on_courses users_on_courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_on_courses
    ADD CONSTRAINT users_on_courses_pkey PRIMARY KEY (id);


--
-- TOC entry 2823 (class 1259 OID 65937)
-- Name: answers_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX answers_id_uindex ON public.answers USING btree (id);


--
-- TOC entry 2801 (class 1259 OID 57584)
-- Name: course_resources_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_resources_id_uindex ON public.course_resources USING btree (id);


--
-- TOC entry 2804 (class 1259 OID 57585)
-- Name: course_resources_url_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_resources_url_uindex ON public.course_resources USING btree (url);


--
-- TOC entry 2797 (class 1259 OID 57555)
-- Name: course_type_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_type_id_uindex ON public.course_type USING btree (id);


--
-- TOC entry 2800 (class 1259 OID 57556)
-- Name: course_type_type_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_type_type_name_uindex ON public.course_type USING btree (type_name);


--
-- TOC entry 2820 (class 1259 OID 65917)
-- Name: questions_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX questions_id_uindex ON public.questions USING btree (id);


--
-- TOC entry 2805 (class 1259 OID 65841)
-- Name: resources_of_course_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX resources_of_course_id_uindex ON public.resources_of_course USING btree (id);


--
-- TOC entry 2826 (class 1259 OID 65956)
-- Name: test_results_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX test_results_id_uindex ON public.test_results USING btree (id);


--
-- TOC entry 2817 (class 1259 OID 65903)
-- Name: tests_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX tests_id_uindex ON public.tests USING btree (id);


--
-- TOC entry 2811 (class 1259 OID 65867)
-- Name: topics_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX topics_id_uindex ON public.topics USING btree (id);


--
-- TOC entry 2808 (class 1259 OID 57640)
-- Name: user_info_avatar_url_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX user_info_avatar_url_uindex ON public.user_info USING btree (avatar_url);


--
-- TOC entry 2814 (class 1259 OID 65892)
-- Name: users_on_courses_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX users_on_courses_id_uindex ON public.users_on_courses USING btree (id);


--
-- TOC entry 2846 (class 2620 OID 65834)
-- Name: user create_user_trigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER create_user_trigger AFTER INSERT ON public."user" FOR EACH ROW EXECUTE PROCEDURE public.create_user_function();


--
-- TOC entry 2843 (class 2606 OID 65932)
-- Name: answers answers_questions_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answers
    ADD CONSTRAINT answers_questions_id_fk FOREIGN KEY (parent_question) REFERENCES public.questions(id) ON DELETE CASCADE;


--
-- TOC entry 2830 (class 2606 OID 49350)
-- Name: confirmation_token confirmation_token_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 2834 (class 2606 OID 74145)
-- Name: course_resources course_resources_topics_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_resources
    ADD CONSTRAINT course_resources_topics_id_fk FOREIGN KEY (topic_id) REFERENCES public.topics(id) ON DELETE CASCADE;


--
-- TOC entry 2829 (class 2606 OID 41158)
-- Name: user fklthmvyi4mp8c4y133xlmvdfq0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fklthmvyi4mp8c4y133xlmvdfq0 FOREIGN KEY (reg_id) REFERENCES public.user_groups(id);


--
-- TOC entry 2831 (class 2606 OID 57604)
-- Name: courses professor_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT professor_id FOREIGN KEY (professor_id) REFERENCES public."user"(id);


--
-- TOC entry 2842 (class 2606 OID 65912)
-- Name: questions questions_tests_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questions
    ADD CONSTRAINT questions_tests_id_fk FOREIGN KEY (parent_test) REFERENCES public.tests(id) ON DELETE CASCADE;


--
-- TOC entry 2835 (class 2606 OID 65844)
-- Name: resources_of_course resources_of_course_course_resources_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course
    ADD CONSTRAINT resources_of_course_course_resources_id_fk FOREIGN KEY (resource_id) REFERENCES public.course_resources(id) ON DELETE CASCADE;


--
-- TOC entry 2836 (class 2606 OID 65849)
-- Name: resources_of_course resources_of_course_courses_id_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course
    ADD CONSTRAINT resources_of_course_courses_id_fk_2 FOREIGN KEY (course_id) REFERENCES public.courses(id) ON DELETE CASCADE;


--
-- TOC entry 2845 (class 2606 OID 65951)
-- Name: test_results test_results_tests_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_results
    ADD CONSTRAINT test_results_tests_id_fk FOREIGN KEY (test_id) REFERENCES public.tests(id) ON DELETE CASCADE;


--
-- TOC entry 2844 (class 2606 OID 65946)
-- Name: test_results test_results_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_results
    ADD CONSTRAINT test_results_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- TOC entry 2841 (class 2606 OID 74130)
-- Name: tests tests_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tests
    ADD CONSTRAINT tests_courses_id_fk FOREIGN KEY (id) REFERENCES public.courses(id) ON DELETE CASCADE;


--
-- TOC entry 2838 (class 2606 OID 65862)
-- Name: topics topics_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topics
    ADD CONSTRAINT topics_courses_id_fk FOREIGN KEY (course_id) REFERENCES public.courses(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2832 (class 2606 OID 57560)
-- Name: type_of_courses type_of_courses_course_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_of_courses
    ADD CONSTRAINT type_of_courses_course_type_id_fk FOREIGN KEY (type_id) REFERENCES public.course_type(id);


--
-- TOC entry 2833 (class 2606 OID 57565)
-- Name: type_of_courses type_of_courses_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_of_courses
    ADD CONSTRAINT type_of_courses_courses_id_fk FOREIGN KEY (course_id) REFERENCES public.courses(id);


--
-- TOC entry 2837 (class 2606 OID 57641)
-- Name: user_info user_info_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_info
    ADD CONSTRAINT user_info_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- TOC entry 2840 (class 2606 OID 65887)
-- Name: users_on_courses users_on_courses_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_on_courses
    ADD CONSTRAINT users_on_courses_courses_id_fk FOREIGN KEY (course_id) REFERENCES public.courses(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2839 (class 2606 OID 65882)
-- Name: users_on_courses users_on_courses_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_on_courses
    ADD CONSTRAINT users_on_courses_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2019-05-03 21:27:09

--
-- PostgreSQL database dump complete
--

