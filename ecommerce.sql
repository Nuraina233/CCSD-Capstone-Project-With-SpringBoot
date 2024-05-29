-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2024 at 03:58 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `first_name`, `last_name`, `email`, `address`, `phone`, `password`) VALUES
(1, 'aina', 'aina', 'aina@gmail.com', '', '', '$2a$10$glS5Llojjspbn9Q3seyGp.g1Xu8hIduWcwAhnS1OSnyBBlhlYIas6'),
(15, 'nur', 'aina', 'a@gmail.com', '', '', '$2a$10$1f6yHYbh4iGFRJaJ0wVbvOTyliO0UzFlLKRM9TCP1JwWN9pcClAeK');

-- --------------------------------------------------------

--
-- Table structure for table `orderproducts`
--

CREATE TABLE `orderproducts` (
  `orderproduct_id` int(11) NOT NULL,
  `order_invoice` varchar(40) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_size` varchar(7) NOT NULL,
  `product_qty` int(11) NOT NULL,
  `payment_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderproducts`
--

INSERT INTO `orderproducts` (`orderproduct_id`, `order_invoice`, `product_id`, `product_size`, `product_qty`, `payment_id`) VALUES
(43, 'c5d9d3ee2f834ee38bbbca22bd5541e9', 1, '39.0', 1, 3),
(44, 'c5d9d3ee2f834ee38bbbca22bd5541e9', 2, '38.0', 1, 3),
(66, '18b6f0d029f54867b6a0405404aea1d7', 3, '37.5', 1, 2),
(67, '18b6f0d029f54867b6a0405404aea1d7', 5, '37.0', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `order_invoice` varchar(40) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `order_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `order_invoice`, `customer_id`, `order_date`) VALUES
(16, 'c5d9d3ee2f834ee38bbbca22bd5541e9', 15, '2024-05-29');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `payment_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `payment_type`) VALUES
(1, 'Credit Card'),
(2, 'Cash On Delivery'),
(3, 'Online Banking');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_image` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` text DEFAULT NULL,
  `color` varchar(15) DEFAULT NULL,
  `category` varchar(30) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `product_image`, `price`, `description`, `color`, `category`, `gender`) VALUES
(1, 'Adizero Takumi Sen10 Shoes', 'Adizero_Takumi_Sen_10_Shoes_Core_Black.jpg', 788, 'Chase your fastest 10k in our latest Adizero racing shoes, built exclusively for speed. The Adizero Takumi Sen 10 have been designed with two layers of LIGHTSTRIKE PRO cushioning, combined with ENERGYRODS for stiff and snappy toe-offs as you transition from corners to straights. Get to the finish line in your fastest time yet.', 'Core Black', 'Running Shoes', 'Men'),
(2, 'Adizero Takumi Sen10 Shoes', 'Adizero_Takumi_Sen_10_Shoes_Green_IG8206_HM1.jpg', 749, 'Chase your fastest 10k in our latest Adizero racing shoes, built exclusively for speed. The Adizero Takumi Sen 10 have been designed with two layers of LIGHTSTRIKE PRO cushioning, combined with ENERGYRODS for stiff and snappy toe-offs as you transition from corners to straights. Get to the finish line in your fastest time yet.', 'Green Spark', 'Running Shoes', 'Men'),
(3, 'Adizero Takumi Sen10 Shoes', 'Adizero_Takumi_Sen_10_Shoes_Beige_IG8202_HM1.png', 749, 'Chase your fastest 10k in our latest Adizero racing shoes, built exclusively for speed. The Adizero Takumi Sen 10 have been designed with two layers of LIGHTSTRIKE PRO cushioning, combined with ENERGYRODS for stiff and snappy toe-offs as you transition from corners to straights. Get to the finish line in your fastest time yet.', 'Ivory', 'Running Shoes', 'Men'),
(4, 'GT-2000 11', 'GT-2000_11_Island_Blue.png', 317.5, 'The GT-2000™ 11 shoe is a versatile stability trainer that\'s functional for various distances. We\'ve updated the underfoot cushioning to make it feel more comfortable and energetic. Key adjustments in the midsole include our FF BLAST™ cushioning. This soft material helps promote softer landings and a responsive rebound.​LITETRUSS™ technology is positioned on the inside of the midsole to improve stability. It helps prevent your foot from rolling too far inward, setting you up for a smoother stride.​', 'Island Blue', 'Outdoor', 'Men'),
(5, 'GT-2000 11', 'GT-2000_11_Island_Black.png', 317.4, 'The GT-2000™ 11 shoe is a versatile stability trainer that\'s functional for various distances. We\'ve updated the underfoot cushioning to make it feel more comfortable and energetic. Key adjustments in the midsole include our FF BLAST™ cushioning. This soft material helps promote softer landings and a responsive rebound.​LITETRUSS™ technology is positioned on the inside of the midsole to improve stability. It helps prevent your foot from rolling too far inward, setting you up for a smoother stride.​', 'Black', 'Running Shoes', 'Men'),
(6, 'GT-2000 11', 'GT-2000_11_Island_Lime_Zest.png', 317.4, 'The GT-2000™ 11 shoe is a versatile stability trainer that\'s functional for various distances. We\'ve updated the underfoot cushioning to make it feel more comfortable and energetic. Key adjustments in the midsole include our FF BLAST™ cushioning. This soft material helps promote softer landings and a responsive rebound.​LITETRUSS™ technology is positioned on the inside of the midsole to improve stability. It helps prevent your foot from rolling too far inward, setting you up for a smoother stride.​', 'Lime Zest', 'Running Shoes', 'Men'),
(7, 'Nike Rival Fly 3', 'Nike_Rival_Fly_3_ghost_green.png', 219, 'When you\'re ready to ramp up the speed and go the distance, take off in a model that\'s built for training and racing. Our latest version includes foam cushioning that\'s softer than ever, and abrasion-resistant rubber for extra durability on rocky roads. With updates like these, you\'ll be able to stay focused as you push towards the finishing line.', 'Ghost Green', 'Running Shoes', 'Men'),
(8, 'Nike Rival Fly 3', 'Nike_Rival_Fly_3_black.png', 219, 'When you\'re ready to ramp up the speed and go the distance, take off in a model that\'s built for training and racing. Our latest version includes foam cushioning that\'s softer than ever, and abrasion-resistant rubber for extra durability on rocky roads. With updates like these, you\'ll be able to stay focused as you push towards the finishing line.', 'Black', 'Running Shoes', 'Men'),
(9, 'Nike Rival Fly 3', 'Nike_Rival_Fly_3_white.png', 219, 'When you\'re ready to ramp up the speed and go the distance, take off in a model that\'s built for training and racing. Our latest version includes foam cushioning that\'s softer than ever, and abrasion-resistant rubber for extra durability on rocky roads. With updates like these, you\'ll be able to stay focused as you push towards the finishing line.', 'White', 'Running Shoes', 'Men'),
(10, 'Gazelle Indoor Shoes', 'GAZELLE_INDOOR_SHOES_CORE_WHITE.png', 499, 'Once confined to the hardwood, the adidas Gazelle Indoor shoes now roam freely. Originally an indoor training shoe, their classic silhouette and premium materials have stood the test of time. Slip into the smooth leather upper and feel history come alive. Tri-colour details pay homage to adidas heritage, while the rubber outsole adds retro appeal. Whether laced up for a casual stroll or all-day wear, this pair\'s iconic style and comfort will keep you moving.', 'Core White', 'Sneakers', 'Men'),
(11, 'Air Jordan 1 Low OG Shadow', 'Air_Jordan_1_Low_OG_Shadow.png', 639, 'Sporting the classic black and Medium Grey colour scheme, the versatile and easy-to-style AJ1 \'Shadow\' returns for another round of glory. Premium leather, iconic colour blocking and signature details (like the perforated toe box and Wings logo) nod back to the 1 that started it all. Step into the \'Shadow\' and put the spotlight on MJ\'s legacy.', 'Black', 'Sneakers', 'Men'),
(12, 'Free Hiker 2.0 Low Gore-Tex', 'FREE_HIKER_2.0_LOW_GORE-TEX_WONDER_BEIGE.png', 829, 'Out here on the trail, hiking is so much more than following the path. It\'s where we learn truths about ourselves, reach new summits and escape the hustle and bustle of everyday life. Hiking is you, out here, in the moment.\r\nSo you can head into nature curious and free from pressure, we are committed to developing the right shoe for you.\r\nThe Free Hiker Low 2.0 GORE-TEX - so comfortable, you\'ll never want to take it off.\r\nInspired by our trail running DNA comes a low-cut shoe, designed to go further over meadows, across streams and along rocky trails. It’s waterproof and protects you from wet weather and stray rocks. The Continental™ outsole maintains reliable grip when it’s dry or wet. And with even more BOOST foam in the midsole compared to its previous iteration, every step you take is softer and more supported than ever before.', 'Wonder Beige', 'Outdoor', 'Men'),
(13, 'Free Hiker 2.0 Low Gore-Tex', 'FREE_HIKER_2.0_LOW_GORE-TEX_SEMI_FLASH_AQUA.png', 829, 'Out here on the trail, hiking is so much more than following the path. It\'s where we learn truths about ourselves, reach new summits and escape the hustle and bustle of everyday life. Hiking is you, out here, in the moment.\r\nSo you can head into nature curious and free from pressure, we are committed to developing the right shoe for you.\r\nThe Free Hiker Low 2.0 GORE-TEX - so comfortable, you\'ll never want to take it off.\r\nInspired by our trail running DNA comes a low-cut shoe, designed to go further over meadows, across streams and along rocky trails. It’s waterproof and protects you from wet weather and stray rocks. The Continental™ outsole maintains reliable grip when it’s dry or wet. And with even more BOOST foam in the midsole compared to its previous iteration, every step you take is softer and more supported than ever before.', 'Semi Flash Aqua', 'Outdoor', 'Men'),
(15, 'Anacapa Breeze Low', 'Anacapa_Breeze_Low_oat_milk.png', 155, 'Back and more breathable than ever, the Anacapa returns with an ultra-ventilated upper and the same sharp focus on sustainability. Engineered for warmer climates, the Anacapa Low adopts a 100% rPET engineered knit upper and maintains the same iconic HUBBLE® heel geometry and Vibram® Megagrip outsole that made its predecessor a trail icon.', 'Oat Milk', 'Outdoor', 'Men'),
(16, 'Anacapa Breeze Low', 'Anacapa_Breeze_Low_goblin_blue.png', 155, 'Back and more breathable than ever, the Anacapa returns with an ultra-ventilated upper and the same sharp focus on sustainability. Engineered for warmer climates, the Anacapa Low adopts a 100% rPET engineered knit upper and maintains the same iconic HUBBLE® heel geometry and Vibram® Megagrip outsole that made its predecessor a trail icon.', 'Goblin Blue', 'Outdoor', 'Men'),
(17, 'Anacapa Breeze Low', 'Anacapa_Breeze_Low_black.png', 155, 'Back and more breathable than ever, the Anacapa returns with an ultra-ventilated upper and the same sharp focus on sustainability. Engineered for warmer climates, the Anacapa Low adopts a 100% rPET engineered knit upper and maintains the same iconic HUBBLE® heel geometry and Vibram® Megagrip outsole that made its predecessor a trail icon.', 'Black', 'Outdoor', 'Men'),
(18, 'Adidas Terrex Jawpaw Slip-On Heat.Rdy Water Shoes Navy', 'ADIDAS_TERREX_JAWPAW_SLIP-ON_HEAT.RDY_WATER_SHOES_NAVY.png', 155, 'Move in and out of the water with amphibious ease. These adidas slip-on water shoes have the grip and foot-hugging comfort for confident traction. Quick to drain and quick to dry, they let you move between elements without missing a beat.', 'Wonder Steel ', 'Outdoor', 'Men'),
(19, 'Adizero Takumi Sen 10 Shoes', 'ADIZERO_TAKUMI_SEN_10_SHOES_CORE_BLACK.png', 749, 'Chase your fastest 10k in our latest Adizero racing shoes, built exclusively for speed. The Adizero Takumi Sen 10 have been designed with two layers of LIGHTSTRIKE PRO cushioning, combined with ENERGYRODS for stiff and snappy toe-offs as you transition from corners to straights. Get to the finish line in your fastest time yet.', 'Core Black', 'Running Shoes', 'Women'),
(25, 'Adizero Takumi Sen 10 Shoes', 'Adizero_Takumi_Sen_10_Shoes_Core_Black.jpg', 749, 'Chase your fastest 10k in our latest Adizero racing shoes, built exclusively for speed. The Adizero Takumi Sen 10 have been designed with two layers of LIGHTSTRIKE PRO cushioning, combined with ENERGYRODS for stiff and snappy toe-offs as you transition from corners to straights. Get to the finish line in your fastest time yet.', 'Green Spark', 'Running Shoes', 'Women'),
(26, 'Adizero Takumi Sen 10 Shoes', 'Adizero_Takumi_Sen_10_Shoes_Green_IG8206_HM1.jpg', 749, 'Chase your fastest 10k in our latest Adizero racing shoes, built exclusively for speed. The Adizero Takumi Sen 10 have been designed with two layers of LIGHTSTRIKE PRO cushioning, combined with ENERGYRODS for stiff and snappy toe-offs as you transition from corners to straights. Get to the finish line in your fastest time yet.', 'Ivory', 'Running Shoes', 'Women'),
(27, 'Nike Structure 25', 'Nike_Structure_25_white.png', 589, 'The supportive cushioning provides steadiness for everyday runs. Experience a stable platform without sacrificing performance with a combination of Cushlon 3.0 foam and a Zoom Air unit. Plus, a higher stack height makes it more comfortable, stable and supportive than ever.', 'White', 'Running Shoes', 'Women'),
(28, 'Nike Structure 25', 'Nike_Structure_25_black.png', 589, 'The supportive cushioning provides steadiness for everyday runs. Experience a stable platform without sacrificing performance with a combination of Cushlon 3.0 foam and a Zoom Air unit. Plus, a higher stack height makes it more comfortable, stable and supportive than ever.', 'Black', 'Running Shoes', 'Women'),
(29, 'Nike Structure 25', 'Nike_Structure_25_dark_smoke_grey.png', 589, 'The supportive cushioning provides steadiness for everyday runs. Experience a stable platform without sacrificing performance with a combination of Cushlon 3.0 foam and a Zoom Air unit. Plus, a higher stack height makes it more comfortable, stable and supportive than ever.', 'Dark Smoke Grey', 'Running Shoes', 'Women'),
(30, 'EvoRide SPEED', 'EvoRide_SPEED_Deep_Ocean.png', 529, 'The EVORIDE™ SPEED shoe is a lightweight trainer that\'s designed to provide more energy savings. This trainer\'s updates focus on stability, a smooth forward roll, and grip.​Key adjustments in the midsole include our FF BLAST™ cushioning. This soft material helps promote softer landings and a responsive rebound.​Lastly, the AHAR™ rubber outsole material offers improved grip on various surfaces.', 'Deep Ocean', 'Running Shoes', 'Women'),
(31, 'EvoRide SPEED', 'EvoRide_SPEED_Sky.png', 529, 'The EVORIDE™ SPEED shoe is a lightweight trainer that\'s designed to provide more energy savings. This trainer\'s updates focus on stability, a smooth forward roll, and grip.​Key adjustments in the midsole include our FF BLAST™ cushioning. This soft material helps promote softer landings and a responsive rebound.​Lastly, the AHAR™ rubber outsole material offers improved grip on various surfaces.', 'Sky', 'Running Shoes', 'Women'),
(32, 'EvoRide SPEED', 'EvoRide_SPEED_Midnight.png', 529, 'The EVORIDE™ SPEED shoe is a lightweight trainer that\'s designed to provide more energy savings. This trainer\'s updates focus on stability, a smooth forward roll, and grip.​Key adjustments in the midsole include our FF BLAST™ cushioning. This soft material helps promote softer landings and a responsive rebound.​Lastly, the AHAR™ rubber outsole material offers improved grip on various surfaces.', 'Midnight', 'Running Shoes', 'Women'),
(33, 'Gazelle Indoor Shoes', 'GAZELLE_INDOOR_SHOES_Semi_Blue_Burst.png', 499, 'These adidas shoes are made for however you move. Once training shoes, now iconic lifestyle must-haves, they\'ve been reimagined for modern life. Suede details accent the sleek nylon upper, which rides on a gum rubber outsole for a retro look. As versatile, stylish and comfortable as ever, the sneakers ready to take you wherever the day leads.', 'Semi Blue Burst', 'Sneakers', 'Women'),
(34, 'Gazelle Indoor Shoes', 'GAZELLE_INDOOR_SHOES_Semi_Spark.png', 499, 'These adidas shoes are made for however you move. Once training shoes, now iconic lifestyle must-haves, they\'ve been reimagined for modern life. Suede details accent the sleek nylon upper, which rides on a gum rubber outsole for a retro look. As versatile, stylish and comfortable as ever, the sneakers ready to take you wherever the day leads.', 'Semi Spark', 'Sneakers', 'Women'),
(35, 'Japan S PF', 'JAPAN_S_PF_White.png', 230.3, 'The JAPAN S™ PF shoes give your feet and your mind a lift for every move. They\'re based on one of our throwback offerings from 1981 but rework the iconic court details with lifestyle materials. Updating the sole with a platform tooling, this shoe is complemented with good comfort and improved cushioning. Mirroring the aesthetic of a heritage basketball sneaker, this heritage style is also paired with nostalgic ASICS branding, like the \"ASICS\" lettering at the heel.', 'White', 'Sneakers', 'Women'),
(36, 'Japan S PF', 'JAPAN_S_PF_Jade.png', 230.3, 'The JAPAN S™ PF shoes give your feet and your mind a lift for every move. They\'re based on one of our throwback offerings from 1981 but rework the iconic court details with lifestyle materials. Updating the sole with a platform tooling, this shoe is complemented with good comfort and improved cushioning. Mirroring the aesthetic of a heritage basketball sneaker, this heritage style is also paired with nostalgic ASICS branding, like the \"ASICS\" lettering at the heel.', 'Jade', 'Sneakers', 'Women'),
(37, 'Nike Court Legacy Lift', 'nike_court_legacy_lift_White.png', 379, 'Elevate your style with the Court Legacy Lift. Its platform midsole delivers a bold statement on top of the clean, easy-to-wear design. And don\'t worry, we\'ve kept the classic Court fit you know and love.', 'White', 'Sneakers', 'Women'),
(38, 'Nike Court Legacy Lift', 'nike_court_legacy_lift_Pale_Ivory.png', 379, 'Elevate your style with the Court Legacy Lift. Its platform midsole delivers a bold statement on top of the clean, easy-to-wear design. And don\'t worry, we\'ve kept the classic Court fit you know and love.', 'Pale Ivory', 'Sneakers', 'Women'),
(39, 'Skyline-Float X', 'Skyline_Float __Ocean_Mist.png', 175, 'The new Skyline-Float X has unmistakable crossover appeal, coalescing responsive cushioning and rugged propulsion in one hybrid hiker. An adventure ready style that employs plant-based elements from top to bottom, we’ve cued up a soy-based sockliner, 30% sugarcane midsole, 89% bio-based Pebax® plate, and Vibram® XS Trek rubber outsole.', 'Ocean Mist ', 'Outdoor', 'Women');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `stocks`
--

CREATE TABLE `stocks` (
  `stock_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `size` double(5,1) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stocks`
--

INSERT INTO `stocks` (`stock_id`, `product_id`, `size`, `stock`) VALUES
(8, 2, 37.0, 5),
(9, 2, 37.5, 12),
(10, 2, 38.0, 12),
(11, 2, 38.5, 10),
(12, 2, 39.0, 10),
(13, 2, 39.5, 10),
(14, 2, 40.0, 10),
(16, 3, 37.5, 13),
(17, 3, 38.0, 11),
(18, 3, 38.5, 9),
(19, 3, 39.0, 10),
(20, 3, 39.5, 10),
(21, 3, 40.0, 10),
(22, 4, 37.0, 27),
(23, 4, 37.5, 26),
(24, 4, 38.0, 24),
(25, 4, 38.5, 25),
(26, 4, 39.0, 26),
(27, 4, 39.5, 29),
(28, 4, 40.0, 18),
(29, 5, 37.0, 11),
(30, 5, 37.5, 10),
(31, 5, 38.0, 10),
(32, 5, 38.5, 10),
(33, 5, 39.0, 10),
(34, 5, 39.5, 10),
(35, 5, 40.0, 10),
(36, 6, 37.0, 10),
(37, 6, 37.5, 10),
(38, 6, 38.0, 10),
(39, 6, 38.5, 10),
(40, 6, 39.0, 10),
(41, 6, 39.5, 10),
(42, 6, 40.0, 10),
(43, 7, 37.0, 10),
(44, 7, 37.5, 10),
(45, 7, 38.0, 10),
(46, 7, 38.5, 10),
(47, 7, 39.0, 10),
(48, 7, 39.5, 10),
(49, 7, 40.0, 10),
(50, 8, 37.0, 10),
(51, 8, 37.5, 10),
(52, 8, 38.0, 10),
(53, 8, 38.5, 10),
(54, 8, 39.0, 10),
(55, 8, 39.5, 10),
(56, 8, 40.0, 10),
(57, 9, 37.0, 10),
(58, 9, 37.5, 10),
(59, 9, 38.0, 10),
(60, 9, 38.5, 10),
(61, 9, 39.0, 10),
(62, 9, 39.5, 10),
(63, 9, 40.0, 10),
(64, 10, 37.0, 10),
(65, 10, 37.5, 10),
(66, 10, 38.0, 10),
(67, 10, 38.5, 10),
(68, 10, 39.0, 10),
(69, 10, 39.5, 10),
(70, 10, 40.0, 10),
(71, 11, 37.0, 10),
(72, 11, 37.5, 10),
(73, 11, 38.0, 10),
(74, 11, 38.5, 10),
(75, 11, 39.0, 10),
(76, 11, 39.5, 10),
(77, 11, 40.0, 10),
(78, 12, 37.0, 10),
(79, 12, 37.5, 10),
(80, 12, 38.0, 10),
(81, 12, 38.5, 10),
(82, 12, 39.0, 10),
(83, 12, 39.5, 10),
(84, 12, 40.0, 10),
(85, 13, 37.0, 10),
(86, 13, 37.5, 10),
(87, 13, 38.0, 10),
(88, 13, 38.5, 10),
(89, 13, 39.0, 10),
(91, 13, 40.0, 10),
(92, 15, 37.0, 10),
(93, 15, 37.5, 10),
(94, 15, 38.0, 10),
(95, 15, 38.5, 10),
(96, 15, 39.0, 10),
(97, 15, 39.5, 10),
(98, 15, 40.0, 10),
(99, 16, 37.0, 10),
(100, 16, 37.5, 10),
(101, 16, 38.0, 10),
(102, 16, 38.5, 10),
(103, 16, 39.0, 10),
(104, 16, 39.5, 10),
(105, 16, 40.0, 10),
(106, 17, 37.0, 10),
(107, 17, 37.5, 10),
(108, 17, 38.0, 10),
(109, 17, 38.5, 10),
(110, 17, 39.0, 10),
(111, 17, 39.5, 10),
(112, 17, 40.0, 10),
(113, 18, 37.0, 10),
(114, 18, 37.5, 10),
(115, 18, 38.0, 10),
(116, 18, 38.5, 10),
(117, 18, 39.0, 10),
(118, 18, 39.5, 10),
(119, 18, 40.0, 10),
(120, 19, 37.0, 10),
(121, 19, 37.5, 10),
(122, 19, 38.0, 10),
(123, 19, 38.5, 10),
(124, 19, 39.0, 10),
(125, 19, 39.5, 10),
(126, 19, 40.0, 10),
(127, 25, 37.0, 10),
(128, 25, 37.5, 10),
(129, 25, 38.0, 10),
(130, 25, 38.5, 10),
(131, 25, 39.0, 10),
(132, 25, 39.5, 10),
(133, 25, 40.0, 10),
(134, 26, 37.0, 10),
(135, 26, 37.5, 10),
(136, 26, 38.0, 10),
(137, 26, 38.5, 10),
(138, 26, 39.0, 10),
(139, 26, 39.5, 10),
(140, 26, 40.0, 10),
(141, 27, 37.0, 10),
(142, 27, 37.5, 10),
(143, 27, 38.0, 10),
(144, 27, 38.5, 10),
(145, 27, 39.0, 10),
(146, 27, 39.5, 10),
(147, 27, 40.0, 10),
(148, 28, 37.0, 10),
(149, 28, 37.5, 10),
(150, 28, 38.0, 10),
(151, 28, 38.5, 10),
(152, 28, 39.0, 10),
(153, 28, 39.5, 10),
(154, 28, 40.0, 10),
(155, 29, 37.0, 10),
(156, 29, 37.5, 10),
(157, 29, 38.0, 10),
(158, 29, 38.5, 10),
(159, 29, 39.0, 10),
(160, 29, 39.5, 10),
(161, 29, 40.0, 10),
(162, 30, 37.0, 10),
(163, 30, 37.5, 10),
(164, 30, 38.0, 10),
(165, 30, 38.5, 10),
(166, 30, 39.0, 10),
(167, 30, 39.5, 10),
(168, 30, 40.0, 10),
(169, 31, 37.0, 10),
(170, 31, 37.5, 10),
(171, 31, 38.0, 10),
(172, 31, 38.5, 10),
(173, 31, 39.0, 10),
(174, 31, 39.5, 10),
(175, 31, 40.0, 10),
(176, 32, 37.0, 10),
(177, 32, 37.5, 10),
(178, 32, 38.0, 10),
(179, 32, 38.5, 10),
(180, 32, 39.0, 10),
(181, 32, 39.5, 10),
(182, 32, 40.0, 10),
(183, 33, 37.0, 10),
(184, 33, 37.5, 10),
(185, 33, 38.0, 10),
(186, 33, 38.5, 10),
(187, 33, 39.0, 10),
(188, 33, 39.5, 10),
(189, 33, 40.0, 10),
(190, 34, 37.0, 10),
(191, 34, 37.5, 10),
(192, 34, 38.0, 10),
(193, 34, 38.5, 10),
(194, 34, 39.0, 10),
(195, 34, 39.5, 10),
(196, 34, 40.0, 10),
(197, 35, 37.0, 10),
(198, 35, 37.5, 10),
(199, 35, 38.0, 10),
(200, 35, 38.5, 10),
(201, 35, 39.0, 10),
(202, 35, 39.5, 10),
(203, 35, 40.0, 10),
(204, 36, 37.0, 10),
(205, 36, 37.5, 10),
(206, 36, 38.0, 10),
(207, 36, 38.5, 10),
(208, 36, 39.0, 10),
(209, 36, 39.5, 10),
(210, 36, 40.0, 10),
(211, 37, 37.0, 10),
(212, 37, 37.5, 10),
(213, 37, 38.0, 10),
(214, 37, 38.5, 10),
(215, 37, 39.0, 10),
(216, 37, 39.5, 10),
(217, 37, 40.0, 10),
(218, 38, 37.0, 10),
(219, 38, 37.5, 10),
(220, 38, 38.0, 10),
(221, 38, 38.5, 10),
(222, 38, 39.0, 10),
(223, 38, 39.5, 10),
(224, 38, 40.0, 10),
(225, 39, 37.0, 10),
(226, 39, 37.5, 10),
(227, 39, 38.0, 10),
(228, 39, 38.5, 10),
(229, 39, 39.0, 10),
(230, 39, 39.5, 10),
(231, 39, 40.0, 10),
(1549, 2, 40.5, 10),
(1550, 4, 40.5, 6),
(1551, 4, 40.5, 6),
(1556, 1, 39.5, 15),
(1559, 1, 40.0, 5),
(1560, 1, 44.4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(15, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `orderproducts`
--
ALTER TABLE `orderproducts`
  ADD PRIMARY KEY (`orderproduct_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `payment_id` (`payment_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`);

--
-- Indexes for table `stocks`
--
ALTER TABLE `stocks`
  ADD PRIMARY KEY (`stock_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `orderproducts`
--
ALTER TABLE `orderproducts`
  MODIFY `orderproduct_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `stocks`
--
ALTER TABLE `stocks`
  MODIFY `stock_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1561;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `stocks`
--
ALTER TABLE `stocks`
  ADD CONSTRAINT `stocks_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
