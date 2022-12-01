-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: interfriends
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

USE heroku_cc550da581ba583;

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` bigint NOT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `number` int NOT NULL,
  `public_local` varchar(255) NOT NULL,
  `user_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FKpxpw7qa7k7scwns6b57iio3xg` (`user_user_id`),
  CONSTRAINT `FKpxpw7qa7k7scwns6b57iio3xg` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,NULL,225,'R DOS CARIRIS NOVOS',1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address_orders`
--

DROP TABLE IF EXISTS `address_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `address_orders` (
  `address_address_id` bigint NOT NULL,
  `orders_order_id` bigint NOT NULL,
  UNIQUE KEY `UK_c3gyiyu6xn5a52155d4fx1t4b` (`orders_order_id`),
  KEY `FKtdwtm4ywri97s7hoqp34toj83` (`address_address_id`),
  CONSTRAINT `FKclgdgutoy6r1f79fvmd4ya25m` FOREIGN KEY (`orders_order_id`) REFERENCES `order_table` (`order_id`),
  CONSTRAINT `FKtdwtm4ywri97s7hoqp34toj83` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_orders`
--

LOCK TABLES `address_orders` WRITE;
/*!40000 ALTER TABLE `address_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `address_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Camiseta','Camiseta'),(2,'Vermelha','Vermelha'),(3,'Preta','Preta');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `coupon` (
  `coupon_id` varchar(36) NOT NULL,
  `expiration_date` varchar(255) NOT NULL,
  `savings_percentage` double NOT NULL,
  `valid` bit(1) NOT NULL,
  `user_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`coupon_id`),
  KEY `FKoyykv23snpujy0dsokwhyyl0l` (`user_user_id`),
  CONSTRAINT `FKoyykv23snpujy0dsokwhyyl0l` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES ('1d1b8522-d2aa-4db4-a020-05b2bf9d133d','23-12-2022',0.18,_binary '\0',1),('370b65a0-2269-4648-8ec3-0a644ea70889','17-12-2022',0.2,_binary '',1),('59030cf0-fe56-4ed8-8140-73eca418f0d1','17-12-2022',0.25,_binary '',1),('9fb6257b-06e6-45ee-a9f6-8cb418294ca1','17-12-2022',0.25,_binary '\0',1),('e96308df-e01c-4c1f-8de0-b5dd5856a7b8','17-12-2022',0.25,_binary '',1);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (16);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `order_product` (
  `price` double NOT NULL,
  `quantity` bigint NOT NULL,
  `order_order_id` bigint NOT NULL,
  `size_size_id` bigint NOT NULL,
  `product_product_id` bigint NOT NULL,
  PRIMARY KEY (`order_order_id`,`product_product_id`,`size_size_id`),
  KEY `FKb5n7kw5krl4q971orvox6i2tm` (`product_product_id`),
  KEY `FKobvaf8vhi4f331lya30cq1cvj` (`size_size_id`),
  CONSTRAINT `FK2e2d4xkwiqbbd6vjmvmu7iid8` FOREIGN KEY (`order_order_id`) REFERENCES `order_table` (`order_id`),
  CONSTRAINT `FKb5n7kw5krl4q971orvox6i2tm` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKobvaf8vhi4f331lya30cq1cvj` FOREIGN KEY (`size_size_id`) REFERENCES `size` (`size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (701,2,2,2,1),(701,2,4,2,1),(701,2,6,2,1),(701,2,7,2,1),(701,2,8,2,1),(701,2,9,2,1),(701,2,10,2,1),(1402,4,12,2,1),(1402,4,14,2,1);
/*!40000 ALTER TABLE `order_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `order_table` (
  `order_id` bigint NOT NULL,
  `total_price` double DEFAULT NULL,
  `address_address_id` bigint DEFAULT NULL,
  `coupon_coupon_id` varchar(36) DEFAULT NULL,
  `user_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKr0px8jj0xvy0a3xhpqqm4v1vh` (`address_address_id`),
  KEY `FK3nsrtc7tqahescthvd46tx24d` (`coupon_coupon_id`),
  KEY `FK89gns61nqhr2ie0880dwip77v` (`user_user_id`),
  CONSTRAINT `FK3nsrtc7tqahescthvd46tx24d` FOREIGN KEY (`coupon_coupon_id`) REFERENCES `coupon` (`coupon_id`),
  CONSTRAINT `FK89gns61nqhr2ie0880dwip77v` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKr0px8jj0xvy0a3xhpqqm4v1vh` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES (2,NULL,NULL,NULL,NULL),(3,701,1,NULL,1),(4,NULL,NULL,NULL,NULL),(5,525.75,1,'9fb6257b-06e6-45ee-a9f6-8cb418294ca1',1),(6,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL),(11,701,1,NULL,1),(12,NULL,NULL,NULL,NULL),(13,1402,1,NULL,1),(14,NULL,NULL,NULL,NULL),(15,1149.64,1,'1d1b8522-d2aa-4db4-a020-05b2bf9d133d',1);
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table_order_products`
--

DROP TABLE IF EXISTS `order_table_order_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `order_table_order_products` (
  `order_order_id` bigint NOT NULL,
  `order_products_order_order_id` bigint NOT NULL,
  `order_products_product_product_id` bigint NOT NULL,
  `order_products_size_size_id` bigint NOT NULL,
  UNIQUE KEY `UK_5d8tn3kxjj6cgkpf92a5eiaqp` (`order_products_order_order_id`,`order_products_product_product_id`,`order_products_size_size_id`),
  KEY `FKdiixmkn0ams5cvgo25ecn63f7` (`order_order_id`),
  CONSTRAINT `FKdiixmkn0ams5cvgo25ecn63f7` FOREIGN KEY (`order_order_id`) REFERENCES `order_table` (`order_id`),
  CONSTRAINT `FKia263ymphnicgsht4t2tlrhn5` FOREIGN KEY (`order_products_order_order_id`, `order_products_product_product_id`, `order_products_size_size_id`) REFERENCES `order_product` (`order_order_id`, `product_product_id`, `size_size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table_order_products`
--

LOCK TABLES `order_table_order_products` WRITE;
/*!40000 ALTER TABLE `order_table_order_products` DISABLE KEYS */;
INSERT INTO `order_table_order_products` VALUES (3,2,1,2),(5,4,1,2),(11,10,1,2),(13,12,1,2),(15,14,1,2);
/*!40000 ALTER TABLE `order_table_order_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `phone_id` bigint NOT NULL,
  `ddd` varchar(255) DEFAULT NULL,
  `ddi` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `user_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`phone_id`),
  KEY `FK9c64a0obud4geupt8p999e5fw` (`user_user_id`),
  CONSTRAINT `FK9c64a0obud4geupt8p999e5fw` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` longblob,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Tenis Nike',_binary '�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\�\0\0�\0\0\0Z<2�\0\0\0sRGB\0�\�\�\0\0\0gAMA\0\0���a\0\0\0	pHYs\0\0\�\0\0\�\�o�d\0\0_\�IDATx^\��t\\\�}\���\0\�\�\rR�H\Z�M\�1MC���`\�r\�\�e#kI�nɛ\�N�j�u�:�n�&]j\�ďZYJt�\�Zj\�ĲT\�ˑ�K։MG�\��ײƌ,\�	���\�s�y\03���\��\�s\�\�`�`0\0�����s��\�g�̜o\�}�\�\���㟝!�B�B�y$�BH�\�B!E�FK!�\Z-!�RD�5t\�5�D!��\�\��\�_4K��GK!�\Z-!�RDh��BH�\�B!E�FK!�\Z-!�RDh��BH�\�B!E�FK!�\Z-\�\\�\�\�\�~\�e�q\Z\��-�y\���V���\�k�\�\�>�\�i��>�O+a��\��\�\�\�o�s|�#e�0��wZ���ђ5\�9\�\�\'\��M��M\�Y\�\�\�O\�\�ee+\�\�1ͥ̆s	̩\�Uz^K\��}V��Q0\�\ns)���\�|�\�i�\�F\�:�FK֔?=�[�l\��\�F��G�\�\��\��\�/[ǳ\��\�(%櫟\��\Z���ъ�cT�\��Xu�\�}\��\�\0\�hɚR\�x\'v\��k:�V�\�6G��\��\�\�]0�F����c&g��k��{x��У��7��\�c~^\�G�3��}L�0�3�뎿�V�er\��a��g��\���au<��\�Jp\r�>c\�w^�\�}\�m�F\n�,��#�S\��\�ҝ\�-��\�\�N\�\�k�\�\�7Ľ�\��V�m�q|�_�y���mm�r���x䄐�ђu�>1~l�\�\����\ruw\�쓾6������M\��71�r�76\����\n\�T�\�\�9�:�6\�\�m����|,}\�\��t�)�}��\�U}\�\�}�_\�v�\�\�\�\�\��N�\�_\n���sv�`\�8=4���;Q��p�2D�/�����{>�6>e�\�Q\�\ZI�=|?�\�ώ\�K\�k\�>�\Z\Zj�\�\�[\�����a9�׻ﴞG�!n�y���L\�*�w�_&\�&\�5\�Ǳ\�\�r�q���\�\�b����[\Z-Yg\�G�ˉQx��/\�^\�7\�\��:޽_\�﵌\��\�\��KVOx\�c�;\�Q�1���\�Mc\�xG\�\�Y=^9�~\�?IٛA\�Zшw\�\�9\��\�\�\�7L\��?��\��\�@��B��5\�}z\�\�_\'���G\�V�r\�\�[Ϲ�j�\�,�7�3�\�O\�\���!\n�.��~m\�\�_��\�\��/�k\�?\��w}7ޙ�\�ehi�\�\�\��\rc\�\�#\�Z�c޹���\�\�\�R\�G�\r�Ҽ��\�؃JKN���\�\�|_�9Nu��\��\\\�\�\�߲\�s���\�h\�:cZ��\��5=\�7�\��4/\�j\�ڻMw�\�j\��Y��\�Asc�YQlAE�:\�\��gew��\�§N�\�\�f1\��0�1�U\�@���ʖ;��Ր�\�^���2���o�{��\�v�\�����\�\�\��\�\����2��j��!�w9+\�?g]U�\�\�av�O\��\�R�\n�z7\�g��홽v\�2�ym2�K��?�_�w\�\��Pn���{�\��Ax4Yş���޺����\�CǛy\�6Y�\�h\�:�\�\�\�\��Ԝ�(~W����llHS.��Ø\�\�.�e2-���/�\�՛���#\�B\�P�\n\�\�v\0C\�\�\�\�g.F�fqi\�п��\�\��֯ۋ�d_�o�bd\��\�\�\����路�~\r�	xv��FK\�\�¹�Z���c��/���]w������7����.�W�\�#鞉�*L�Z5\�\��\��\�}=�\�V\�\�\�\�U�W�鏼YS�\����wр|deϡ�?�n�W\�P\�.D𓧤\�\��ctj�i�\�_��}դ��	I�\�t���φ�\�~��\�H�z�A|�\���\Zf����\�\�\�\\�\Z�ax\�\�8�]]+\Z:�q�\�ܨ޺�:�~n��U5�FK\�Ux\�7�[�\�\�\�\�Ëj2��\�Y��\�H�H��\����\�\�\�P�I\�c����	?��/Y�/3S��mÅ\r�\�⟽\�\�\�5��\�ǂY\�\�\��_��zr�{�Ԛ�d��7\��~\�L�\�s�ɫIGI�ڞ���Yϥ\�\��T\��>�\����\��)�r���\�_,dX7k\��\�P\�^\�g\��\�-e�����\�;�X\�ƙ\r�\�S�\�\�eU�2�\�R\\�|��g�Ɍ9��Y\"d�I�~cjT��\�\�\�.\�>ܸ��Y14�<n\�m_#\r\"�\�\�e�\�_As\�&.��ZѴ_]\'�#\��w��|cf��cx@*���\�*�\�\�\�\�:խ\�F\�Bl��/d\��\�\�\�Gz���ٓ{2�\�ƽ��\Z8��\�IIYe�\�}|�\�eѿ�C�\�\�\�\�\�\�\�\�W�Ո\�˛z����vޅ�> d�\��\�_4K�\�h	!��U �\�r\�B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"�\��h\�\�~\�B�娯o���\�o�~��B!��\�\�h[v��Y#�BH.F�{�\�\�m\�4k�B\�\�L\��J~�Q\�Y��`0F�\�ϒF�kw��`0ұKWo�Y#�BH.b\�\�x�B!�\�h	!��\"�\�б��=f�B!����q\�B)K�hkZ��5B!�\�bz�*{��BH)X�\�\��\���`0�t\�gɡ\�\�\�}f�BV\�O�+�D\��\��\�\�,\�\�H��\�:���=f�BV\��/q����Y#d}�ޗ���7��\��n�u��\�=��\�\��?y\0��\�\�\��]\�P ��D\�pFF�ũ)�\�sO�z@\�\�\�\�z��\�\��v0��\�b\�{F\�I��²ͫ\�#x⼔�RGتcX\�8�z\��8�\�K*@B�K�yM\�|i*���\�\�� \���T\�\�M/D��߽���!%�\�\�CkYZdy�D�V�\�-FW\�,Q�BK����\\�,\�Mu\��[�hm\��&\�\�CsS��d{�:�L�\�z,m�[�0\�\�\�R�Ja�D�\�m����Pxћ��b�\�5ssV�2�ƭR\�/Q-e*M/z�\�%��\�\�\�\�h\�&�F���\Z�L�U&���z0\�G\�\�\�-7aI�U\�c5B\�,G\"��F\�83��Z1I��\�\�:��\�\�!Yu\�3�+����w�\�\�+$�ı\�\�*\�\�j%W3#f*\�%F��\�~=\�#lr\���-��X�p�S\�a�\�$+\�R��\"����r�\�!f[az��Du2�\�\�0j\�4e<\�U)ђJW1��yW>)S��J�\�\�\�W.\��z0\�G\�\�\�-7a\��\��`0V#\�\�VLL\0f\"�f%��D\�$��\�	��\�Q)c{�W}F�Č\�a�,D\�!:\\ǕGw���6�rˍŸu\'C\����\�o\�k_�u��;s�B��\�\�\��@��\�*\�]_����9;���Z�>�%\�T#\�N��+�Nn5�a�ʩ=Ɲ�j\�+���9�+Y\�\�[g06n|\�7\��ٻ\�\��wܟͅ�\�½�F�3]�*ՕVn=�\n3\�W\�\�\�-7�\�\�\�\"/\'~\�7�O|\��N��7q\���B\�!\���\r|\�w\�\�Yfm\�<AE\�uc\�=�\�_\�\�K\�g����/���|~�!�\�<u�6#_��>^\�\�5C���l\n\�\�a�̣Gܯc}�M�\�rc1�\�Ѫ�\�/\��+e!����w��\�_��7q]�c/Vó�(���?����_��\�9��\�qK�\�3�`\�q\�d��?c\��\�eT�\�-�ls�\�ߟ�\�\�\��u\�\�W�W\�\�F{\�՛���\�C\�s\'\�\�l�\�g(\�|641\r���N�^�P��oEܯc}�M�\�rc�\�\�z�w�\�x�.=����t��7�WT\�=\�\��G,\�U\'�X[|�\�MJ��\��\�\����|o��4^\�\'��y��\�\�x�\�7���s�\�\�v�e\�_�\���\�\�2_�\\뙚�����i\�\'a�\�\�q���Me��y�꿾K��ϝ2\�o\�\��sӭ����o���qk��[��{�+!\Z\�\�f17������\�x�5�ާ\�ro��\��\�\�Җ��\�֌Bۻ��cWj�ژ\�guY\�\��\�>�\�nǄ�\�f\�\�3߰\�&dcp?\ZH,����\�\�x+*��=\��1�W�>\'�(,c\�X��\�0e�\�xW��.\�h09��h��l�y�������\��^P\�\�\�b\�8f�\�\�:�m\�8�l���1\�_\�\'?�NiC\��m̪\"\�:��q㵿�1~��x\�44�\��\�\�`\r[��?�Kx\����?��\��Q���p7\�\�\�Ϫ���u�\�~=\�#lr\��P�\�P\����Q��w�s\�hԿ�@ow���я^崷�\�\��o�J_����`Lm.ۿ�o\�\�\�\\�\'�\�6:��\�P!=\�\�-\�ٗ�*���ɷ\�`l\�\�\��\�\�\�[C�Fx\�\r�{v\�c\�3(q�; \r\�\�\�\�\���f䫪P]�\n�\�~=\�#lr\��Q�k�Җ�\��ݱJ���Ƈ�z��ﵮ\�_\�s�\��\��m~^�V\'q�{v\�\'�<�\n\�~>t�\�\�\�\�?�^��׷\�>Bʼ\��P[�\'li6j)׺:\��\�>�\��\�?�N���m\�w\���t\�\"�S�ޏ�0�N�ٟ\�P�\�\�9��\�����ׄ\n�\\g:/��b?�[?�l\�Z��cI�\�\�=\�k/|�V�~�s��g%~Mz�������\'�\�v[\�ǚ\�Q|ӕ�柿��\�#���z:䤡;\��v6N9i\�O\�\�\�Re�\Z\�\�\�U\�\�R\�\�/��Q\����y�\���\">����\���\��ҟ\�\�~\����s����½�\�o��B��\�X?a�+�\�X�%\�G�\�]0k��2����\�_�\��z�\'?;\��db8�\�w\���\�\�\�S��.z\�j�\�\�85;�ϫ\��L\\\�Vʼ!e\�x\�UӦ��\�GR\�$7�	)3��Kʼt.�O�̯��}�^\��Ra�7�x�\'\�\�G�k,��`0�����\�f\�56bVEu5��\�	^��&KWQQ�\��$W!�O�s\�\�\�E�IZ���s�ܼhs*东�Kn\��z0\�G\�\�\�-7c\�\�}d���\n�\\_��f��\n1ǘ���\��~K��*+Cܤ$�\���\�P)e\�\�:y\�\�:�.uHĝ���2	)=.\�e�R\�\�ݯc}�M�\�r#?\�\�2�5�9eJ�\'}>*+u�dY\�E��\����\�t�e�S	)�Q �!s{Y/z\�\�%\�\�ɧS\��\Z�2\�O�>Q�P\�W\�~���6�rˍ\�XF��BV��\�$�\�\�&q]�\�\�_/z�\�U�R�;�\��2I\\�x;�\�pȺ���\�\�:7�B`\�\�n&18(1�\"��1s\\ytr\�A�%����PW$z�A�LOc\�\��΋�C\�*\�lG�	�H��)�ܘ�\�\�Lz\�`D\�V.�Ѡ��Or���H�\�0xÔɣ�[3�5�9\�\�\�(�NL�O̱7C�\�\�h]E��pG\�ܠ��&e�$w=��\�/\�x\�{ߖ:��Bz���\���\�ʕ	y\�j��=�����`���ɕ[n,ƒ_\�i\��A�F!+\�\�?<��pڬ�>P\�K\�Jޛ����\�=K\Zm+���J|Ŝ\�Y��\�\�Ȋ��=\�\Z!�Br1�\�E\�?XA!�\�,\�h\�E^��`0�#?K\��sf�BV\�3�韙%B\��\��\�,\�pO��k���\�!�F!+\���O\�d�-�F\��@�/+yo�\\�\�BJ�\�h��\�\�c(�L\"�h8�##�\�\��`= \�\�\�i�\�\�\�a|;\�\�b1�=#e��̌��N�v�\���RƃHa��a�\�\�\�A<�\��/�\\\0	}�)��\�T��?:��z\�d\�)�\�r2!�$��|h-+C�,o��ժ7�\�\�*�%�UBh��2����\�z��λ\�b\���@��)��7�\�\�T��&\�^�\�.S�N6/�e(��f\�>�ԤR\�&\�\"q\�\�,\�\�O*zћ��b�\�5\�\'k*Sh\�*\��\�R�Ҕ�W]�y\�,�>���m�k�hH��A\�\�Xe\n\�ݯcm\�&Wn��\�\�BJC$��h�33h�����W]\�\�qT\�9D\�/���|ue3\�\���W\�z�Ė8��\\E�W�\�jf\�4\�Ĩ�\�d�B�%���p>1\�Jy���H��z\�˕f��m�\�\�J\�\�jgè\�w��o\n[�^�-�t3��w\�\�2Ua��\�|�}��\�\n��R\Z+&&\03�I�]E\"aB\\�DT����=ͫ>#zDbF\�}�\��\�*X\'��Mb��p��p\���\��]F+�ԡ\�y􈑲i?�\'�6w\�d�\�!Q=R;���]���f^\�\�\�2vj%��z��S��;\�:��u�Y)�Ѧ�/�!B\�\0\�8\�\�\�\�8~���\�x\�\�?M�nPf\�a3[l8ԙ.\�|�Bu���GB�\�&\�Ѯ#�.\�\�ǿ�Ow��5\�n\�\�E�Q28r\�\�~�~\�\"-x\�\��\�\�cF�\�\�^\�}��\�\�bá\�QE6�\�\�`}摐eRB�\�n5�Z\�\��;�Z\�\�܅v�\�d��w�\�=\�{\�lgN4-}z\�M���\�W?���|�<=�\�\�1�u\�\r<��2ɫ0��c�\��\�\��к=\��\�\0�\�6\�\�n�N)�\��6\�\�Y-곣�J3��^c4N�\�\�E9	�Gs�\�[.�m.�+�\�,6H+�j�E\�w�\�m�\�}���n\�K�5:�g]\��\�y�7�\�^�\��޿�k\�7��unD��[��Ugvd�H\�H\\>�~\�\�9ļm#��!\�#%1ڎ�M\�$��������\�t�\�st�X�a�9�\�\Z3<�e��бS�\��[��\�I\�\�Z\�It�5��\\7:�L\�X��\�\"ח~v�\�k�X\����c\�A`�~��IRf\�;���Ӱxe\�͑\r��~\\�f	Y)%1ڶ:\�Ѻ\��f]GRC��\�\�\��q\�0/\rh0��\�c\�y\�<�S�w\�]\�Ď\�RB�5-=�r\�|6ٛ%dE�\�h\��=�\�5D��Yy��/B/���ݱT�Ѿ�e�\�{��\�0\�\�\rB�Iz$ơ�J\Z�Q�\r��>ܚ\���\�|�\n\��E<P�\��<i�P[wg����z����f�S\�߄��=\�\�i\��\��Y_[\��\Z\�R世�b\\\�\�z�Y\�d(�ƨ\�O�}{���n\�f5l�l�3�f+$r�\�\�*)��mz�\�\n={W_�T�!\��t\�h�U\��5C\��\�E3��uy=A\�.�p\�q\�闭ITj�\�ޯ:�8v\"}<�~�����\�\�\\\�Z\�H\�3{�!���\�=\�s\�nd�\���qԼ׏֍\�\�\�\\=\�#�\��\�9��QQ�\�\\=\�\�\�	Y�%\�G�\��zd����X���i�\�V\�ˏ~�z\���\�K_w~21�J\���	\�\��\�)\\���j5Ǉ\�q���\�\�U\�S&�\��*eސ2\�m���:�M#RǏ��Hn~RfN��y\�\\�<Y�.\�b�o�����\�~oza1�,\�\�1!���s���UQ]\r\�s���\�\�UTT�s>\�U�\�S\�\�\\�\�z\�}R�\�%*|\�$7/ڜ\n9.�\�R�N6+4ڢ����f	qQ_��f��\n1ǘ��\�q�\�\�%�\�\�7)IB݃\'ɡRʘo<x\�\�:�.uHĝ���2	)=.\�\�L1)T\'�\Z-!�$LIL�|TV\�ɲW=(\�k\�\�b�\�\�	N%�L\�F�\��̭_�\�A[�\'�NY=ک!)3.��\�\��\�q��\���Rn&���\�\�uY��|�\��FW1�J!h\�\�ss\\\�L$qM\�\�@\�!\�n9^�~�\�\�x\n�i���\�\�Ā�\�\�\�q��\���RzB!\\�\�	\�3=�As\�;/����5&\�3 e�\�scJc3e<\�=��[�F���>\���\�#\�\�\�\rS�@�l^h����petW\'&\�\'\�\���zo �u=\�\��s�R暔\�\��n�y\�{ߖ:��Bz���\���\�ʕ	y\�g��7�l^��BȚ\��z!\�b��FKY3\�\Z!\�\Z-!��NY\�/y��B)\"4ZB!��p\��f�\Z-Y\��\Z-!d\�\�Y\�d=\�YǄ�M�\�h_�^\�P ��D\�pFF�ũ)�\�sO�z@\�\�\�\�z��\�\��v0��\�b\�{F\�I�u��0^\��\�L���\'��\�\�\�F\�k����\�\�󡵬-��]�V�\����h��V	��F\�\�J�F�끦:o:!+�FK)	5��I�H\�6;�z�^�f��\�.z��	ƚ\�\Z�J9�D���4e\n\�	Y	4ZBHi�DP��qf\r�b�^�j�\�\\<�j;��eQԕϠ�lFz�^uB�C�%���p>1\�Jy���H��z\�˕f��m�\�\�J\�\�jgè\�w��o�Z�N�wh���\�X11��L��\�*	�\�\'$��G��훅ꄬ\0\Z\�j\�~O>rN=r7N��;�\�8q�(�l0T�\��*�DWa\�E����9;���*T\'d\�hw\�\�C\��\�\�#fC\�񻳶9�c&�&\��rmK�q\Zd*4\�2?WO\�e�M�:\�m�3(T\'\�E\�\�\\�|\\\�\�(��\�v\�\�\�;r\��\�B\�d�g{��]��\�\�\��P\�\�	\"ۼo\�<g^�纍BH&\�d�\���d�W�\�ޅ=7/9��O\�0�MH��l\n\�	�@э֡+��^�U/��c��o[�2\�\�ҍ\��\�߳uz\�\�IW\�^�����\�\�_\�\�\�6wY9���\�\��\�^|�|�c6��hv�Ŏ)W���\�}m\�ͭ���֍\�\�<\r��\�w�=rus�+!넵3\�#\�Ѧ��\�\��Ut)��m�WZ\�ʀ��Op̻��\�\��[\�\�/-��!�\�vP\�e���\�U\�Xh\�혒2\�ӎ�Z��s\�x[�:F�M\�`�u��7��\�@�S�{�{c\�<��.cG���\r�|\�d��r*;o�d\�\�\�\�\�\�r4��Q+\�9;W\�9�\�\�\�h����2!\��S<ݥs2���G\�a�܄\�F�\�d\�)c�\�^��ֆn\�\�\�i�~��D\�e��?QC\�r\�\��9�]\�\�i��ձv\�%5��r\��J�\�{�������{�����\�l����1铮BN�Gs4H\�ah�n6\�H톢i��/\�\�:�eu\�\�\�\�B��\Z\\��dz��\�����w�]y�\�Us}\�2\��w\�E�Et\�4rӀ\�!�F{�\�����к�l��:\��z\�\�I\�z\�\�Y��:O�\��8\r�`n-�\�{�\�<\�y�_\�yXrd�R0k0t|Oߺ�����k�V�Z���\�T3D\���\�Y�CȎ)�py��A�\�h鑞M��u<�z���sb������\�R\�$��\�\�\�\ZR\�\�%5\n㯳F�\�&UC��\"�#,�\�:��|��\�	�\�\�\\�\��������U\�@\�M���p���k�m��& ep\0�\�\�Ց��\n\�X\�*Ѝ\�ƈ\��\�WߏՍ��q��?=�}\�\�r{�٦���N!ǔn\�ب�\�\�r2\�\���$��y8�\��t��L�}w8\�\��D\�\"�u6,�\�t�\�xd\�\�N.U×\��z�z\�\�5i\�hk\�\�lbk�F\�u��\�\�^��i�����u\�R�{��1u�\��\�8n�\\�t\�z.w��\\^	��m�VX\��\���\�nbL\�\�����묫A*�G뺬�`_\��\�&D�Ps�R\�	�\�GKY3\����x\�\�\�O&�S)|W?!���=�\�ᰴ���\�x�fg�y�{�����\�J�7���6\n\�y?\�\�\r\�GKٜ47c���*��a\�\�N�75Y����t\�\'�\n\�|*��+7]\�BuBV\0��R\Z\�\�ÌTU!\"\�3�=\��[�\�TY\�&%I�{�D 9TJ{>D�:!ޡ\�BJĤχ@e���,{Ճb��>.f;]n�~\�TB\�D��l�mA\�\�^�P���@�%����\�$�\�\�&q]�\�\�_/z�\�U�R�;�\��2I\\�x;�\�pȺ\�O�:!+�FK)	=��H��虞Ơ���]�\�U�َ\Z\��2�S\�1%�1��2ꄬ\Z-!�$\\\�Չ	�9�\�b\�_�\�h]E��pG\�ܠ��&e�$w=��c\�tBV�\�CY3\�_\�!d�P\��\�h	!k�}B#d=B�%�B\�)��%�\�B!E�FK!�B\�^�%\�^�%�lx8똬G8\��ip\�ы\n0�H \Z\�\�\��85��{\�)X\�zxzZ\�wrx\��X}\�H�!)3�\�\�ƫ\�#x⼔�RGتcX\�8�z\��8�\�K*@B\�UH�yM\�|i�w�\�@\�hy��RZ|>����E��K\�j՛\�bt\�\�*!�\�H�Z\�\�\�r=\�T\�]o�u�\�F�\�ܠ��ɇ\�245\��u�!64ZBHI�I��M�E\�\�Yԛ�T��7]\�v\�k\�O0\�T�иU\��%��L�)\�E�2�D�\�Y�}V\r\�$\�(ѐBS����\�bC�%���H5\�(\Zgf\� Q+&\�U�6�\�\�㨶s��_E]�\�\�f��\�]���\n�-ql5��:9�Z\�\�̈\�\�q�Q\�FK)\r\�0|b���X)FY�Lz\�˕f��m�\�\�J\�\�jgè\�w�5e<\�U)ђJW1��yW>)S��J�\�g�!ĂFK)\r����ȤY��\"�0	!.zB\"*zT\�\�\�U�=\"1#u8#Ģ\��\�\"\�p\�m\��q\��p\�C\�0چ���T\��\�\�8\�n4B6�Gj�\�V��0��\�\�\���R\�N�D�U��p��u\'\\\�E��HF�\'R\'���\����1:\'\�ɥɷ�\\ۖ��qf?OB֚\�\�VVC\�i��c�}�Vu�\�b-fP���r�H\�2)z�v\�\�w�\�\�gG�������\�x`�OB8\'\�<۫Z�\r8z\�N\�\�\�\�\�W1���\�-mj]�~=^\�\�]F#D���s�?/�h�7\�H�\�:k�T��3&�\�P\�\"�Bue�\�E�%Pt�u\�\�\�]\�Ъ\\��C̷�N���K7s\�{����t2Z\�K��\�\�\�f\�\�Ó\�\�\�\�]h7߭k;h\�\��>y\"�k�,\�\�%]ϣ\�]۹�?\�\�q�\0���؝�9�\�\�=�[��&y#F\�\�5�\�\�b}\�!k\�\�\�\�h\�I�F�p�*��S���\�{�*��\�\�纕�4�y7`�^�6��\rr���[\��\�\�o��z\�\�l�ʱZ�l�\'��Ϙ��w�.�$1����d�O�\��\�yk]#���j_��\�\�\�]�\�/�\�ڶx\�q�\�\��\�wY�(#�?{4�2`u\�\���.�S\�\�f��t?\��\�4�:�7�?ҟ5\�a}\�W����ݳ\�f\'D{�\�\��\�.\0˞�B\�[nv�c�\�K\�̻s\�hݞ�ĢOHjad\�\ZF\�2�t\�u\�:�\n>}ì,�jDsS�����n���g���ץ70�\��u\�\�\0�A#u��M^\r/\�\�K\Zt��wy�\�\��\�\�C��ݸ,\�(���|�\�\'ދ���\�܈4���B��\�hӽ<u��eH\�N��V\�mZ\�g-c�_zo�Hd��V\�8r\�2�,\�χ3|ݺ״���Ma\�ۋ�ؾ�\Z\�\�!üt\�1\��ToG??�E�����\Z\�9��x���-3\�\�h��؜�8���*\�NY\�`\������\��݅\�7A��:O\�	���ߟ\�\�K{�\�.��h\�=ތ�R�{m\��\��fw�^��_ۗzM�\��\��!�o\�9\�C\�yz�d\�\Zq\��\�\�9?\� \�ĥBu\'A�\�5Z5Le����hM��Λ�\��w�\�\Z�8g_�\�v/\�\�:�G��Me���:\�ud�J\�<\�d��\'��\�\�h�;a�r\�ƓY\�L�5廆�&�/\�\�`�>��\�\�E\�B�Pi\�uO�\�\�km����m8jӠ\Zq�}{\�G}I!=?�\��\�\���A�,ٗ06�\�t^t�\�\��\�e�jW��\�\�:��\�0\�\�\�\�9=-5|�y\�w��]�2&e\���\�C�Y\��\�kW\�C\�\"f�V��3�\���ݥs�~�H\�˙\0e�zN+�Fڍ\�̤)\�2\r����\�ǽ\��\�zD�\��uW\�o\�G\�\��W�:\'\�\�\�~\�0	q��z��z�����-!d\�pߏ�⥯;?�N��]y��\��{���\�@T7\�1�\�\�\�,>�r/�2qu�X)󆔑V�\']\�1m\��:~$u�@r�{��2s긤\�K\��\�I�~�ޏ��9in\�\\c#fUTWú\�\�Eoj�t\�Or��T�1Wn��^t�ԡu�\n_:\�͋6�B�+oׅܪ\�h	!���Q1\�HU\"b�1#{\�\�~��KL��!nR���O�C���g��\�!uh]ꐈ;?%eR&.z\\�k�\�K�M��R�$&}>*+u�d٫\��q1\�\�r\��R&\nd�@lB\�V�^���K���O��\�Ԑ�}B�r�¶9bA�%����\�$�\�\�&q]�\�\�_/z�\�U�R�;�\��2I\\�x;�\�pȺ���\�\�:7�B`\�\�n&18(1�\"��1g\��\r��RzB!\\�\�	\�3=�As\�;/����5&\�3 e�\�scJc3e<\�=��[�F���>\���\�#\�\�\�\r�!64ZBHI�2:��\�s\�\�п�7к�\�\�ڹA)sM\��I\�z7Ǽ\�oKJW!�\�@\�\�zFq\�ʄ<qE��\�\�	�\�\�{!k��\�=�����\Z-!dͰOh��Gh��B\�:e1�\�5ZB!��\�h	!��\"¡cBȚ�k�d=\�k���\rg��gB6\rn�}1zC�\0f	D\�a\\���\�w\�=\�YOO\��N\�\�� ���\�)3$efԝu\�x�{O��2�@@\�[uKg_\��\�x��M@���\�h	!%�\�\�CkYZdy�D�V�\�-FW\�,Q�BK����\\�,\�Mu\��[�hm\��M�	Y\Z\Z-!�$ԤR\�&\�\"q\�\�,\�\�O*zћ��b�\�5\�\'k*Sh\�*\��\�R�Ҕ�W]�y\�,�>��1Y4ZBHi�DP��qf\r�b�^�j�\�\\<�j;��eQԕϠ�lFz�\��*[��\�\�V\'G\�\�\�h	!�!�OL�R+\�(+�\�F��r�\�!f[az��Du2�\�\�0j��f\�\��W�DK*]\��\�\�:Y\Z-!�4VLL\0f\"�f%��D\�$��\�	��\�Q)c��W}F�Č\�A�%˄F��t����:���G6\�q�\r�\�\�\��@��\�*̽h5�>g���S+\�gգ$\\\��E2\�]8����\�\�:+=b618\'u\'\�ɥɷ�\\\�B~��x��.�E\�6��Q�\�t[�\�\nՕVn	Y&E\�\�\\�|\\\�\�(��\�v�n�l�r8r\��\�B\�d�g{���=q@�s\�\�\��P\�\�I\"ۼ\�\�\�<���\Z?u�F#\�o\���$\�w�����\�E�\�N\�\�EZ��\"��\r�2G\��+�U�,\Z-)���CW�\�CC�^p\�\�1߶:�^.\�x\�1\��=ۜ-�\���t2�\�\�}�!<�ui 7\�\�坜\�Ǒ\��\Z6�\�\�\r���Z\�U�\n\��/����<��\'\�\�\����w�2\�^N�x\�pzGٯ_���KJ\��\�h\�\�\�]\���\�\r��^\�kg�C@\�v�R\�\�h�lG�^H\"4���Wѥ�R̷�\��pTpt\�>׭ĥq̻���j�[�\�Tݭ{#Цr����W\�\�պ�c\�\'\�\�(��E�Vo\�걫v�\�~\�\�]hW_jw\�ۡ\�\�z\�n۸߁=�K�!\\>��\�]7.�P�A�\�6神��<��~\�梜P���b~�\��ܝi\�F�^��%\�u\�\�e��s\���_�6=\'�8���\�\�r\��\\\�\��C#qy�Z\r^B\�\�Rt�\�\'tգ\�\'c�\�ޟ:�\�\\�\���G��Bڨ\�\�\�\�f\�;�^\�@�u\�4;\\кw��4:�7\�iI�z]�C݀�a\�~er�_\�%\�\\&��\'u�uu\��l�\��t�4,\�}��ﶦ��\�~.�sN��>�*\�u�\�\�g����sM��p�\�L\�1�\�·�\�]!\�\�׌\�\�%#�7k�dc���`\r�\�^2�VAN�/�z:\��\�\�S\�|�N��E�\�O\�\rh�\��y9v\�\�\�\�U�\�g7�b�an�죽\�\�z^�ѧ^:�\'���:\�і�\�\�ouXtH_�T�����\�\�u�uB�!ܟћM\�n�B�\�\Z\��\�߷��fɦ�����5j�#��\�^f^3\�j��{����\�!�\'Y\��JV�2��H~CS\�gy\�k�3��\���5G\�#?K=�\�a�\�8�\\�us\ZL�p8))\����\�=uC�h�G&�Z�{o�o\�R������\�\�\Zm\�<l_\�s�/\�@\�M\�\�\��k\�\�\�;R,~�>�G�5Re��L1{h9=Tl�>���gM�Yb\�\��u��m\�ᇪ\�n�V��\�<�\�\�H��Z�5\�\�s�\�:���\�:\�\��z��U:�7�\�\�ܗ.\�\�\'���\�\�=�b_\�lTH\�:\�y\�UR kc�\n���aZe���{@��k����p\�\�&V��=�\�\�Փs�	8�\�\�\�*ҽi�\�h\�\�9=i\�1X�^\�xp&@��\�6}��)k��=��9\�ئ�\�ƅ}u\�,��V�\\��)kҚ~�\�����\n}�X\rc/wB)�p�W[�|s\"\���l�߼w\�\�VW�$č�\�\�ꉮ�N\��~�%G}�ǺV\��\�ٺdS\�\�\�K_w~21�J\���	\�\��\�)\\�\�d\r\�\�\�85;�ϫ\��L\\\�_Vʼ!e�%\�IWuL�:F��I?\0\�G�	\��h7;G�Z\�\�!gBn��1\�؈Y\�\�pn:\�Eoj�t\�Or��T�1Wn��^t�ԡu�\n_:G\�\�hK����%&_�騯GT3RU���c\�\�^��\�o\�See���$�\���\�P)e\�\�7�\�qHZ�:$\�9:���\�h	!%aJb\�\�C��RGH��\�A1^[��.�Lp*!e�@@6\nĶ dn!\�Eںĸ8�t�=Z�<h����p3�\��x\�$�\��؜5\�\�E\�7���T\nAs��\�Rf\"�ko�Yw���]\�\�SL[9B��FK)	=��H��虞Ơ���]�\�U�َ\Z\��2�S\�1%�1��2����֭\\�A\�J0!�B�%���+���:1�>1\�\�X�+\�{��\��2פL�\�\�ps̻\���ԡtI�V���\�\�{!k��\�=�����\Z-!dͰOh��Gh��B\�:e1�\�5ZB!��\�h	!��\"¡cBȚ�k�d=\�k���\rg��gB6\rn�}1zC�\0f	D\�a\\���\�w\�=\�YOO\��N\�\�� ���\�)3$ef\�\�x\�x�{O��2�@@\�[uKg_\��\�\�}I\�H��\n�2�I�/My\�\���y�����\�1|�\�u\\\�a�~q/>1\�I\�x\�x�o����1�;w9�\�r\\o\�qyѳ_�~y��<~\�Lf��7Fp���\n\�ŷ��W�b-�\�BJB�χֲ2�\��v�Z�z\�[���Y�Z%��\Z)S+�\ZY��\�\�-�.\�\�\�\�n\�z�\�Mehj�\�%\�\�2�m.]�\�\�\�\�\�CS�\�\�	6ݖ.\�Io1��m�Z�:|R���2Ԉ^\�\nTH�^�\�햮�Jr\�~\�\�X\�X&\�\0�^�\r��RjR)l�h��mv�\�\'�\�\�FW�]�\Z��5�)4n�r~�j)Si\�xѫ�.Ѽu~�UG\�6\�5J4�\�\� ej�2^�&KW��Q�G�UGm\�<j�fQ+\�\�6Ϣ�\�*\�M��t����\�(�\\�l[&�~4e<\�R�\�%\�%�\�ԁ\�y\�\��Ma�V\�5��o0h���\���&\ZE\�\�\Z$j\�$�\�\�F׹x\�v�ˢ�+�A]ٌ�t�\�U�^!�%��&WQ\'\�U+��1M9.1j�zu���h�\���WTVDQYCe\�|�3(\��WK��s\rq�U[�9\��\�RwRb\��=\�j]E�&�y�<R>\�UĐ�\�PE�ܛ�Ѡ\�BJC8��d�<V�QV$���r�\�!f[az��Du2�\�\�0j��fMzUJ��\�U̠j\�U�O\�T�ᯒ\���U�^U!�]EU�d�UG%\�\� C��\r�BLL��w�*�ueRʭ:�K�BHT�%��Ye<\�A������#\"�B�\�sM\�\�4�o��F�FK)\r����\0�Y��\"�0	!.zB\"*zT\�\�\�i���\���\��Y��Ct��kE����H�nń��\�eV�����m\"�[1���	���B\�4\�\�M&\�\'0��2\��-T\�hܒF{\�\�}8��ĉF!��9�Gj�\�V��0��\�\�\���R\�N�D�U��p��u\'\\ǵ\"]�S�b҉yW��\�V.]Ǭ�ۑ��\�Aڕ\�Vn^\�J\��Z\�\�7e�/T\�h\�hw\�\�C\�̲\�\�\�\�6���\�~Of|�\�Ii�v\�$:��R8�Q�s�\�t[�\�\nՕVn	Y&E\�\�\\�|<��a2�\�\�s\�x�\�6\n!��\�*����;7Ҁ�.3\�h�>��Ѕ<\���\��Ι���݅G���FD���l\nՕ��\�#!ˤ\�F�Wk\���:\�\�8\�.\'�\�w[\�ݝ\�2\���/\�9��\�!\�Cf;k\�9��i᧏E�\�j\�g�x�\�p�\"\�A8���^!^9�JF��L�W�ͬw�~��7+��ΛI�eK\�=yO?�\n\�\�e�k}Q`[G��Bi�VN^��\��~e8�\��YK�\�\�\�x�K�,�Q�д�峎���I�G\�B�_�Q-\�\�y\�L3hm�\�\�r�A��G�\����r\��[�\�\Z��.�؝c�\���}\�\�5�нu�²�OH�P=XD\��3���:y\�F\"\�4\�4�A�&K?\ZE\�F�\�F\�v\�Ջ3=J���m8�?�[��.f��\�>9t��g�\�W=\�#ۭ\�yt��L!ml>\�ٟ\��v\�/\���WܱS�I��\�Pr\�q\�\�\�=\�\�(Ω^�H\\��<v\�\�s\0�[գ��E{8\�\�,c�]�>>W/��բ�\�\�\'o�\�_\�m�G\�hk\��\\J9vb/\�\�3�\�B\na��\�f\Z]\�\�\�^�\�o�� \�Fj�[\�\n\�3\�&�z��TVk]\�4\nt\�\0\�\�\�,.d�O�W\�e�{[\�ՈU�K\Z�\�:����Fj�֍\�Y\�g \�3�:6t�C�c4*=\�ֽK\�lL�\�\�J/�Y\�ȭ(l\�U\���\'n-��\���s�\n�\'\��\�~\'w#\�1\�\�&\�\�Syz\��|�\n\��E<P:���}M�\����\���\�8C\�f����5�,=ڣ�II\�zha�#\�D��^ls\0�f}\�!?ݸ<�}h?���y�!u\rw�\�d(�\�h�mB\�\�\�L8��r�1Y\\Ń\�d�\�t^t�Hэ6�\Z��>����\�O��f7\��Ĩ\�͚\�d\r\�\�C8\�|𥌙p\�C�w���z��3�b�3Cs9��\�k\�\�3�ʧ��Wc��B\��&��\�\�~�Ya7B�\�V��u\�\�s(̜\�lb\�\r\�u�\�\�ꉮ�N\��\��Ѫ��6M54�ZՄ���\�^��u\�?�N��]y��\��{���\�\�\Z�\�-�\�q���\�\�U\�S&�\�g*eސ2g<ꪎiSǈ\��#�\���߃���S\�%e^:��\'OzӓRǬ�<�\���\�f�G��\"��\�f\�I�W_J\�K����a�g�:nJ�\�Y��#\�%7:5�x*������n��7}2\�TܪcP\�zv\�O\0��U)3*\�5�@$�\�p��_.\\\��h	!d947c���*����\��75Y����t\�\'�\n\�|*��+7]R/�O\�кD�/��\�\�E�S!\�\�t]\n\�\�\��K\�\��0�r\�U�V>ۈ�\�j�[�\�u�C\�*�\�V8�*\��\���\�H�\�v^�j�\�/�WK�N\�\�j�G�\�\Z\�\Z\�Tވ\�r?j+�װP}��n�V��	C\�\��9��GT3RU���c\�\�^��\�o\�See���$�\���\��Rƾ�T��ԡu�C\"\��t��IH��\�q9.kJ�P�>��:D�r�<\�\'T�\�j$\"UHM�9�\'\�I��k]\�ܔX�y�*%W��FY�\n�\�|�ѓ^%\�\�7#zX夎+We\�T&�P9[)�����o4أ%��uG�I��\�J!Y��\�xm}\\\�v�\�2����\�F�\���qyу�.1.\�4��zWSCRf\\�	\�\'\�\n�\��\�,]\�>LO�\�!uNM�0�\�Ը4\0B��=Xa\�:\'\rS�\�Ԉ�\�\�*\�\0$\�qy\�G+�mB\�\�l\�:\"���\�\�+%dc\�\�\Z-!�$\�L&q=\�5�\�<6g�=z\����b �B\�\��\�渔�H\�\�ہ$�C֝l�\��F׹�\�&w3��A�)���\��߸a\�j�Y�\���\�\�`\�DR\�*\�I�a\�*Rb�Va�3$��\�9�\�UƋV�͸,Kn(���UGl4�鉸\�	\��װP}�A�%����PW$z�A�LOc\�\��΋�C\�*\�lG�	�H��)�ܘ�\�\�Lz\�`D\�V.�Ѡ��Or���H�\�0xÔ�KJ�x�W\��\�\�2��ư��\�u��\��\��\Z¨\�h_c}\�\rYe�\�#J�\Z\�1ާLҪ#0D\�F��iDG�2�\�\r\Z-!�$\\\�Չ	�9�\�b\�_�\�h]E��pG\�ܠ��&e\���\�ps̻\���ԡt\�kM�gW�L\�cW\�8��V�,]\��Uy\�p���J\�q]���\�0d��7]��\�%.\��0/\�\�Q��L`�W\�Q�kʔ񢏾���\�DORz�&\'�\�\�\�RF\�\�b�1ϽP}��n�\�C\�|��\�C\�z�\�_\�\�B\���F\�z�FK!��S�K^�%�B���B)\":&���FK\�3�FK\��p\�1Y�p\�1!d\�\�6\��1`&�@4ƅ�|qj\n}�\�S������\�\�\��0�\�k����2CRfF\�)\'�W�G�\�y)\�A���Uǰ\�q�� ��ql\�\�$C֞b-�\�BJB�χֲ2�\��v�Z�z\�[���Y�Z%��\Z)S+�\ZY��\�\�-�.\�\�ԙ�M�4ZBHI�I��M�E\�\�Yԛ�T��7]\�v\�k\�O0\�T�иU\��%��L�)\�E�2�D�\�Y�}V�,��R\Z\"\�D�h��A�D���W�\�\�:��\�\�!Yu\�3�+����w�\�\�+$�ı\�\��84ZBHi�\����\�J1ʊ��1��\\iv�\�V�ޮ$Q���v6�Z}�Y�毅\�U)ђJW1��y�B�FK)\r����ȤY��\"�0	!.zB\"*zT\�ؾ\�U�=\"1#u\�g\�2)�\����܇S�1҆bN>d��\�wi�\��\�z�\�C�С�\�q\�U���B\�C�H\�pz�\�Jt\�^��yY��C\�ة�\�\�Q�jY�\"mڄ2\�n�l7�, ]fc\Zo�8r\�z\�\���\�\��i�e�\�(th?�\'9�cfuӢ\�t[�\�\nՕVn	Y&E\�\�\\�|\\\�bH\�|h�\��Pw\�1�K<v^�\�\�/[\���\�4Z�8�y]�\�6\n!i���W1�$�.\�0[��{[pK|KE���l\nՕ��\�#!ˤH?X�z�w�]>�\�h-#U\�h�%Ks�_B\��\��n\�e�I����^\�ç\�I$;�D\�ٗ�t�YTk��}\�\�b��6,\�\�\�\')\�E���t\�\�eb\�\�\�7:�g�\�\�ڗ\�U߂cQ\�y՛=h\�ؘ}���G[eݵ/5��5C�5�q,\�mB8��+8c�d�\�gu�鿵���W\�k��v�`\�\��\�ϝk�aY��\�\'$\�o��p=,J4*k��\�)�\�K\�CrCRfV\�LK�7��T\�IHSRG���\n�\�\����\�l\��H\"4bn\�\�.�\���8�\��&�N\�H���ֺ\�-+l��s��z\�\�\�h\�ؔ�H\�g{�@\�\�C�R\�6#�\�ۘ�u|\�\�]\Z\�Wݸ9A곏\'�\�qN�6�\�C|\�_I?7e�j�<�e\�d��\���\�+\�5\��\�B6+pt\�u1\�d	!k\�\�\�\�V/3:�NW\�P:�7Y=ʑ�t/UI�\�\�эs\�H��\�\�pv�c�\�{\�N:���-\��7�#\���\�\�u\�\�\��(\�<\�-�\�\'�BƜ�5���)󷯡�{�\�\�\0�\�r\��3G��jݞu�m�c麀��\�7��\�8�m\�\�!dM)�Ѷ41\��=�jr^h�\�\Zfu\�є=�뢮Nz�\rhȻ�\r��ܢ�^�\'���](\�u\�f\�ǎ�\�Ȧ��\�go����v��T�\�$��H~#\�\\d,7��ӽ��\�\Z\�.6vO4\�\��DW���(�ؓ�e9v��[�7�o\�R������Jp�vi\�ê\�t^�4C��\�_:r\�\\\�\�\Z���\�\�\�\�\�J\�!\�%NFN���WB�񧇶௲�3\'ݸ�	>\�\�o�v�{��\����o5q|\�\�V@��n�\���0�\�t^t�\�:2\�x��\�uE=�GN�9\'\�\�\�\�INΰ�F}eȝS3�յ\���c5�ș\0e�uϼ͇�\�����o+\��s��\�\�@Cv���+\�)4\�:\�<ߧU_�q&@\�vj8:c\�1�\�؅��\���4ORM����\�g\�[��\�OT/4WOt�tB����%��_\����\�uR)|W�\�u�\�)\\w�\'ǩ\�Y\�\�=/�2q)�2oH�3uUǴ�cD\���\��~�g3��\�B6\'\�͘klĬ�\�j87��75Y����t\�\'�\n\�|*��+7]R/�O\�кD�/�#d	h���\�P_��f��\n1ǘ��\�q�\�\�%�\�\�7)IB݃\'ɡR\�\�s \n\�\�:�.uH\�s�t!�\�BJĤχ@e���,{Ճb��>.f;]n�\�TB\�D��l�mA\�\�B֋�u�qq�\�{�dy\�h	!%\�f2�\��8�I\\�\�9k�׋\�ot��\�>7ǥ\�D\�$\�$1�\��\�E\�7�΍���r�,��RzB!\\�\�	\�3=�As\�;/����5&\�3 e�\�scJc3e<\�=��[�F�Ε`B�FK)	WFGqub}b����W��ZW\�#=\�Q;7(e�I�>\�]�\�\�w��m�C\�*��!K���B\��\�{Y/�\�=4ZBȚa�\�Y�\�h	!��u\�b~\�k��BH�\�B!E�CǄ�5�\�h\�z�\�h	!\�:&\�\�:&�l\Z\�F�b�\"�\�$��ø02�/NM�\�{\n\����\���Ʒ�A|-C\�3RfH\�̨���j��8/e<聀\��\��:ξ\��?����\��PwRe^�2_�ʫ\�9W�m��FK)	->Z\�\�\�\"\�\�%j�\�Mo1��f�j�Zj�L�\�jd�h��غDk#Pg\�G\�\�\�CsS��d{�:�L�\�z\�h	!%�&�\�6��\�fgQo~Rы\�lt\�E�1?�XS�B\�V)痨�2����\�\�\�[g\��Yu4l�\\�DC\nM\rR�\�*�O\'�4ZBHi�DP��qf\r�b�^�j�\�\\<�j;��eQԕϠ�lFz�\��*[��\�\�V����㪕\\͌����b:���\�BJC8��d�<V�QV$���r�\�!f[az��Du2�\�\�0j��fMzUJ��\�U̠j\�U�O\�T�ᯒ\����S\'�\Z4ZBHi���\0\�D&\�Jt��Iq\�QѣR\��:���\���\�	]�\�pW^�\�jlh�\�8~7N=rN=tF+kU!��Gj�\�V��0��\�\�\���R\�N�D�U��p��u\'\\ǕW\'�\ZE2\�]8���2�G\�Q�iI<zĈK�\�\��\�x\�\0\�\�v7N���\r�����9�pN\�Ó\�w}�\�t[�\�\nՕVn	Y&kУm�~\�w�c�\�,��\�\�/\��ǿ����N������U\�\�a<�/�s\�}+qn�G3Fd�\�ޅ=7/Y\�m��O\�0�M�2G\��+�U�0\Z-)��m4�D\�\�\�J�;�\�|�,�\�ַ\�	SƜ\�M���V^�l3�ts�z���CxRm�vK_�f��M/�\�~�\�Y=\�\\C\���Wa���\�\�:�\�\�6E\���[yo��\�ޫ8c�3G�7�Ì\�t�푫�\�\\	Y\'\�h7\'mݭ\r\�\���GB09eZ\�f\�\�Uݲ~�7	�\�5\�tO?u	]Qkہ�V\����\�z�輩���g{t\�\��]�i�\�\�n\�w\�ǯ�cK�\�\�\�e<ݥ�?\�L\�v/<n�\�߂�7��Y�tE�dC��\�V\'\�_�����\\BH\�(�\��\� ��>\�\��\�C>\�?n6\�\�\��n�\�y�\�2<W|98\�Z�\�^j\��&h��9��u�j6�\n;ށ��\�\��X\��:�NՀ\�\�h d%X�N�\�w,h�Y\��t�\�j,\�G\�\�\\\�q	!+e\r�њ���!�\�L�\ry��\��\�_ݸ<��\�!��[\�\�;\��l�ǎƩ{�W-ǡ�]��\�(�j/!š��Oх<\��X\�e�$B����\�OAY-\��h\�C|y\�2��q\�Q�By\�.��!g�\�pp��\�$:\��\�ݦ7��8�\�\�/!\�!=b�C5*�ς5²�)�2\�&}�書T��\�$(\�51Z\�\�\�s\�Pk&9��\�nMJ\Z\�_p�t\�\�!��\�z\�\�AgD�zl\�\�,\�3-\�x�\�\�Pd�8�G\�mA@_ưP\rR��;�\�\�w�\�\�(\�TTH\�:\�y\�UR kc�K��>\�L(�����F�1K\���\�0�\�]K��=|�p\rz8M\�\�\�s]4k\��\�	Rb�zvs\�	\"K/!�&=��\�#�:�5\��+溬���*\�f���\�\�ꉮ�N\��~���5\�}?ڋ��\��db8�\�w\���\�\�\�S��\��\�b8ǩ\�Y|^\�^0e\�ꞰR\�\r)sƣ�\�6u�H?�:~ ��=HH�9u\\R\�s	|�d~���]�~���\�Is3\�\Z1���\Z\�M\�/zS�����H\�|��ͧ�r\�%�����KT�\�9Hn^�9r\\N\�%�Nn5h���\�P_��f��\n1ǘ��\�q�\�\�%�\�\�7)I\�{�D 9TJ\�y^�8��Kq秣�LB\�\�E�\�q9SA�\�\�V�FK)	S�>��:B�\�U��\����\�t�e�S	)�Q �!sKX/z\�\�%\�\�ɧSV�vjHʌ�>!�D9Bas\\ytr\�A�%����\�$�\�\�&q]�\�\�_/z�\�U�R�;�\��2I\\�x;�\�pȺ���\�\�:7�B`\�\�n&18(1�\"��1s\\ytr\�A�%����PW$z�A�LOc\�\��΋�C\�*\�lG�	�H��)�ܘ�\�\�Lz\�`D\�V.�Ѡ��Or���H�\�0xÔɣ�[\Z-!�$\\\�Չ	�9�\�b\�_�\�h]E��pG\�ܠ��&e�$w=��c\��޷����^j hr=��reB��\"�\�?��Nn=��BȚ\��z!\�b��FKY3\�\Z!\�\Z-!��NY\�/y��B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"[>x���fy�\�1~1�z�\�\�!���x\�\�峘_\�h	!��[�\���CǄBH�\�B!E�FK!�\Z-!�RDh��BH�\�B!E�FK!�\Z-!�RDh��BH�\�B!E�FK!�\Z-!�RDh��BH�\�B!E�FK!�\Z-!�RDx\�wBȚ�\�G?f�Y|걿2K�\��B!%�=ZBȚ�z�+\�5R쑖b�hi���5\�m��>~CC\�\�$�1�\���\��]�=��\�F�֗\���h\�z�\�\�\�\�)��ӱ���]�>\�\�G�\���Q\�\�0^�� ��|l\�2��\�Kj_$VݯJ\�OH\�JW\�\�\�\�\�qV�\���<+S�\��L\��Pu�.u�8�s\�\�\�\�n)s\�*�K\�\��\�\�h9tL)\r���5���:*\�-���5�e���KTHn)\�\�b\�*�J�{\�W�\�e�\�ԛ�d\�۶K9�\\�2.�v�{{�\�R��|��mRGM\�\\��rty\�o�-�P\�M��\�P�:�k�r\�29t�|h���\�P=�9\ns5�[g�\\\�84�䌞\����\�\�,���m��\�\�-_5us��F�\Z\�\�ֆ�^\�8k\�*��PYkձX�\\z\��8\Z��hlL�Y��f��b[C\n��\Ze\��Y�^틣~k\�բKl�J�_aաr�vnk\n��t�\\:Y>4ZBHIHTD$���\"^C�<��\�*\���\�D����/�\'�-]E\�\�|���\���*9�ʚiT\�GQYCyMZ��핮��>�21;��L.��&�\Z\�\��Q�jL\�J�;�DM[\��X�L!:�V�^&��\�u[�Xu��_\�\�%W�\�t�\�:Y.4ZBHIc\��AD\�B�\�Ocf\�:\�ψ>SČ/$!z�2��	���\� Qf��\n\�W�\�[����\�H��\�к�-�3��\�X�LbkE�ʐ\�4\�+Һ�<�u��<\�M��)H��2%u$$7+1?�\�yS�\�&ES1\'\r��ȫ�eC�%���\�H�c<1���4B���t;&�\'�\�e\�*&��\�\�\�r��u;R\�^2usR��[\�WƭC\�ӑOW��#S/t_.]^k\�%f$\"1�\��M;U!e\�k>�,\Z-!�RD�l��p��p\�w\�d��	!k±�\�O\�\��\n\��;q\�L�lr�H�$r9@��l�\�dI�k�\�\��3^�U�\�\�e�	!\�F�\�Q\\Ń�Ǌ\�M\�&���&�\��1�\�QF�\�4�+�|6�t�$E5ڎ�Mp|V�\�|:̲MF�;g\�\�\�+�\�\�#&%\�\�<N\'\�\�\�a�ų̕�\�\�hw�c�\Z�\0.^ŀZ�7�\�5|\�q�nm����T���=%�\�\0}\�.��햷�����&d1:���\�o�y\Z�>�\�k\�9lLH�(�\�\�+�B�|��GԲ{�\�e\�?��N��ő\�hS�\�Itv��BQ��\�ϙ\�a��O\�\�\�i�\��\����FlF�\��s\�F\Zp��CF�!+�hF{lw��02����\�z��}{M˹\r\�q\�\�V�\�i��ޭ%B\n\��UtE�ϓ8sqQ�հ%��*E2\�د���{����\�x\�`�/\�\�M\���]�,!��$᯳?si��\�\��,6{X\�&<\�\�ɲ(�\�\�C�\�\�\�\�\�c�m����=�l�\�\�����ۂ�.sV\�v\�\�M�E\�B:/O\"*�\\纬���.\�X�\�g\�s8v�~3�D69ꬿ�\�\�r�|:YEy鲇�m\�\�c�n\�\�\�g���.\�i\�\��F\���?���M\�A\�PU<�Ϻ�KɃ���˚\�\�A?�Φ��\�9	\�D��J2C�>Tl��OEY9\�M�i�\�\�С��=a1�,#�\�9�yۗڃ\n[M\�a\�*\�ݻ\�\�,ԭ/�V�\�\�\�9wr�2\�[D\�!��2�îF\�e�.e�\�ɲ)�\�\��|o\��+�wk\�o\�\�\�=\�g{\�\�`��&Genc\�c�e\�\�P�\�����xO\�	����\�Sy&$�:l\�6\�vl����A���J�\Z	�o��FK^�����͑[\�*%\�c�\�6n]E��\�,��[���#��\"]G�^\�\\��\�Q)u�:�J[�2\�\�W\'˦��G;-j��:r5\�\�	!��*ԣ*Y��x�RUr��\�\��F/��\�\�f�\�bze\�_L\�*\�O\�\n\�W\\�D�\Z�HR\�c.=\�׺��H��E\�\�\�!z4Q��<\�H�1緃\��\�a\�*\�dݤ)S��\�!��3�\�JH�\�Gͧ�\�RZ�\�nU��\�rKP6]�٩J��Ĉ�}���\�$\�B��Ubn\\LpBz\\\��\�\�R~L\�*\�d\�v��}MG\�1�\�t�J�3!��]�ISǢer\�ѱr\'\��TQ��2a�����B\�\�\�\�+S��}F���\�L\�Z��\�Mƌ.ۄR�\�d��\�h	!�,3c)D\'����$1�n\�6=�B\�FS\�7g��_Z7�\�A��y\�E.�L}l(�1\�\�d?c7f\Zw\�7%��s�b�\�(�C��\�\r��Ay�\��XZ�it;4u,V� }B4�\�&dyr\�z\�Un�\�%F\"�\�d�\�h	!%ab ���&n�Ft\�:���1\��\� F{�\ZZ�~U��A�\����\\:W��\�\�	\����<\�q\Z\�i�m�\�\\\�&F�:+�K\�\�\�\�[S\�2%�1�	;z��\�x+�Q�<-S�\�\�\��)�i\��@\�ASGF.��\�\\e\�:Y>4ZBHI\�\�p_\0ý��F�i�^\�\��gc=QD\�^�~e×\'�}��\�v\�\n\�Wo�0\�\�\�\�<Fqå�v�i]\�~&�TS\�ber\�\�o�[�\�7\'\�}%��\�<NK�t����\��;��L��\�=}�<\�uR�\��$\�\']��r�*\�\�\�\�\�:Y>[>x��֘B����\��E�F!+\�ˏ~�z\�\�\Z!\���T�佹�_�GK!��h	!k�\�s d=R�-��BY!:&�BJ��B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"B�%�B���B)\"4ZB!��\�h	!��\"�\�\�?;o�0t\�5�D!��\�\��\�_4K�,j��BY:&�B���B)\"4ZB!��\�h	!��\"B�%�B����ʍ5���\�\0\0\0\0IEND�B`�','Tenis Nike',350.5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `product_categories` (
  `product_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  KEY `FK7cpkh0ajt3apyej1vtjsvbbeb` (`category_id`),
  KEY `FKppc5s0f38pgb35a32dlgyhorc` (`product_id`),
  CONSTRAINT `FK7cpkh0ajt3apyej1vtjsvbbeb` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FKppc5s0f38pgb35a32dlgyhorc` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,2);
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_order_products`
--

DROP TABLE IF EXISTS `product_order_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `product_order_products` (
  `product_product_id` bigint NOT NULL,
  `order_products_order_order_id` bigint NOT NULL,
  `order_products_product_product_id` bigint NOT NULL,
  `order_products_size_size_id` bigint NOT NULL,
  UNIQUE KEY `UK_23x02f06xp7u4exy7y0m7j804` (`order_products_order_order_id`,`order_products_product_product_id`,`order_products_size_size_id`),
  KEY `FKk3a7w9klwqcooku3eqwk1xkp3` (`product_product_id`),
  CONSTRAINT `FKjs9fuc8d9dis2mcvpoenf6ybw` FOREIGN KEY (`order_products_order_order_id`, `order_products_product_product_id`, `order_products_size_size_id`) REFERENCES `order_product` (`order_order_id`, `product_product_id`, `size_size_id`),
  CONSTRAINT `FKk3a7w9klwqcooku3eqwk1xkp3` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_order_products`
--

LOCK TABLES `product_order_products` WRITE;
/*!40000 ALTER TABLE `product_order_products` DISABLE KEYS */;
INSERT INTO `product_order_products` VALUES (1,2,1,2),(1,4,1,2),(1,10,1,2),(1,12,1,2),(1,14,1,2);
/*!40000 ALTER TABLE `product_order_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_product_sizes`
--

DROP TABLE IF EXISTS `product_product_sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `product_product_sizes` (
  `product_product_id` bigint NOT NULL,
  `product_sizes_product_product_id` bigint NOT NULL,
  `product_sizes_size_size_id` bigint NOT NULL,
  UNIQUE KEY `UK_cggx1qxavujq0defkxk23ic39` (`product_sizes_product_product_id`,`product_sizes_size_size_id`),
  KEY `FKt5aae8ck46iq525aiyrgubq10` (`product_product_id`),
  CONSTRAINT `FK3rcxfahfgtj3o05hiaqpordge` FOREIGN KEY (`product_sizes_product_product_id`, `product_sizes_size_size_id`) REFERENCES `product_size` (`product_product_id`, `size_size_id`),
  CONSTRAINT `FKt5aae8ck46iq525aiyrgubq10` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_product_sizes`
--

LOCK TABLES `product_product_sizes` WRITE;
/*!40000 ALTER TABLE `product_product_sizes` DISABLE KEYS */;
INSERT INTO `product_product_sizes` VALUES (1,1,2);
/*!40000 ALTER TABLE `product_product_sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_size`
--

DROP TABLE IF EXISTS `product_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `product_size` (
  `quantity` bigint DEFAULT NULL,
  `size_size_id` bigint NOT NULL,
  `product_product_id` bigint NOT NULL,
  PRIMARY KEY (`product_product_id`,`size_size_id`),
  KEY `FKmyx2oadrk74jgt9ulgbm6ngyt` (`size_size_id`),
  CONSTRAINT `FKlyplliw6j7l88ij72k0cvr99i` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKmyx2oadrk74jgt9ulgbm6ngyt` FOREIGN KEY (`size_size_id`) REFERENCES `size` (`size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_size`
--

LOCK TABLES `product_size` WRITE;
/*!40000 ALTER TABLE `product_size` DISABLE KEYS */;
INSERT INTO `product_size` VALUES (38,2,1);
/*!40000 ALTER TABLE `product_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_iubw515ff0ugtm28p8g3myt0h` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER','ROLE_USER'),(2,'ROLE_ADMIN','ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `size` (
  `size_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`size_id`),
  UNIQUE KEY `UK_8mx44qvbn71lwrou3igoc1nwm` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,'P','P'),(2,'M','M'),(3,'G','G');
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size_order_products`
--

DROP TABLE IF EXISTS `size_order_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `size_order_products` (
  `size_size_id` bigint NOT NULL,
  `order_products_order_order_id` bigint NOT NULL,
  `order_products_product_product_id` bigint NOT NULL,
  `order_products_size_size_id` bigint NOT NULL,
  UNIQUE KEY `UK_hq1o32g6pg318k8jpavsshn6x` (`order_products_order_order_id`,`order_products_product_product_id`,`order_products_size_size_id`),
  KEY `FKs9menxn5061cfvfxvyydi9ike` (`size_size_id`),
  CONSTRAINT `FK5cbxoi3bonjl8bm9owvqaa4wa` FOREIGN KEY (`order_products_order_order_id`, `order_products_product_product_id`, `order_products_size_size_id`) REFERENCES `order_product` (`order_order_id`, `product_product_id`, `size_size_id`),
  CONSTRAINT `FKs9menxn5061cfvfxvyydi9ike` FOREIGN KEY (`size_size_id`) REFERENCES `size` (`size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size_order_products`
--

LOCK TABLES `size_order_products` WRITE;
/*!40000 ALTER TABLE `size_order_products` DISABLE KEYS */;
INSERT INTO `size_order_products` VALUES (2,2,1,2),(2,4,1,2),(2,10,1,2),(2,12,1,2),(2,14,1,2);
/*!40000 ALTER TABLE `size_order_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size_product_sizes`
--

DROP TABLE IF EXISTS `size_product_sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `size_product_sizes` (
  `size_size_id` bigint NOT NULL,
  `product_sizes_product_product_id` bigint NOT NULL,
  `product_sizes_size_size_id` bigint NOT NULL,
  UNIQUE KEY `UK_i3k22m7u7gupo3s5emt320jag` (`product_sizes_product_product_id`,`product_sizes_size_size_id`),
  KEY `FK55pb7oypahoorok5lvofkgy6x` (`size_size_id`),
  CONSTRAINT `FK55pb7oypahoorok5lvofkgy6x` FOREIGN KEY (`size_size_id`) REFERENCES `size` (`size_id`),
  CONSTRAINT `FKpfv9it21d0680ksomj28xsdvd` FOREIGN KEY (`product_sizes_product_product_id`, `product_sizes_size_size_id`) REFERENCES `product_size` (`product_product_id`, `size_size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size_product_sizes`
--

LOCK TABLES `size_product_sizes` WRITE;
/*!40000 ALTER TABLE `size_product_sizes` DISABLE KEYS */;
/*!40000 ALTER TABLE `size_product_sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL,
  `birthdate` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'12-03-2000','navarro@interfriends.com','Leonardo Navarro','$2a$10$qtj4y7C7MvvFNYtGNXouCu5CfWoMLgqAI6b5LfkW.hQePw.wXR6DS');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_favorite_products`
--

DROP TABLE IF EXISTS `users_favorite_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `users_favorite_products` (
  `product_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY `FKxtb8rq4cptentx3k65jgffco` (`user_id`),
  KEY `FK784yj69we17vffarjyeloiu9b` (`product_id`),
  CONSTRAINT `FK784yj69we17vffarjyeloiu9b` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKxtb8rq4cptentx3k65jgffco` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_favorite_products`
--

LOCK TABLES `users_favorite_products` WRITE;
/*!40000 ALTER TABLE `users_favorite_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_favorite_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-30 10:28:26
