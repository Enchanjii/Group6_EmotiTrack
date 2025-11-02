-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2025 at 03:54 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `user_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `journal_entries`
--

CREATE TABLE `journal_entries` (
  `entry_id` int(11) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `prompt` text DEFAULT NULL,
  `entry_text` text NOT NULL,
  `mood` varchar(20) DEFAULT NULL,
  `entry_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `journal_entries`
--

INSERT INTO `journal_entries` (`entry_id`, `user_email`, `prompt`, `entry_text`, `mood`, `entry_date`) VALUES
(53, '2@gmail.com', 'How are your feeling today?', 'hunter\n', 'Happy', '2025-10-31 16:10:10'),
(54, '12@gmail.com', 'How are your feeling today?', 'asd', 'Happy', '2025-10-31 16:26:01');

-- --------------------------------------------------------

--
-- Table structure for table `mood_logs`
--

CREATE TABLE `mood_logs` (
  `id` int(11) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `emotion` varchar(50) NOT NULL,
  `note` text DEFAULT NULL,
  `log_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mood_logs`
--

INSERT INTO `mood_logs` (`id`, `user_email`, `emotion`, `note`, `log_date`) VALUES
(154, '2@gmail.com', 'sad', '', '2025-10-31 14:43:04'),
(155, '2@gmail.com', 'sad', 'sad', '2025-10-31 14:45:50');

-- --------------------------------------------------------

--
-- Table structure for table `task_completion`
--

CREATE TABLE `task_completion` (
  `id` int(11) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `task_name` varchar(100) NOT NULL,
  `completion_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `task_completion`
--

INSERT INTO `task_completion` (`id`, `user_email`, `task_name`, `completion_date`) VALUES
(80, '2@gmail.com', 'logMood', '2025-10-31');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `signup_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `full_name` varchar(255) NOT NULL,
  `xp` int(11) DEFAULT 0,
  `badge` varchar(50) DEFAULT 'New User',
  `active_challenge` varchar(50) DEFAULT NULL,
  `challenge_progress` int(11) NOT NULL DEFAULT 0,
  `challenge_last_completed_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `signup_date`, `full_name`, `xp`, `badge`, `active_challenge`, `challenge_progress`, `challenge_last_completed_date`) VALUES
(18, '1', '1', '12@gmail.com', '2025-10-19 13:53:12', 'ceejay', 40, 'New User', 'Grateful3Day', 1, '2025-10-31'),
(24, '2', '2', '2@gmail.com', '2025-10-31 04:24:56', 'cj', 125, 'Hopeful Spark', 'Grateful3Day', 1, '2025-10-31');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `journal_entries`
--
ALTER TABLE `journal_entries`
  ADD PRIMARY KEY (`entry_id`),
  ADD KEY `user_email` (`user_email`);

--
-- Indexes for table `mood_logs`
--
ALTER TABLE `mood_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_email` (`user_email`);

--
-- Indexes for table `task_completion`
--
ALTER TABLE `task_completion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_task_day` (`user_email`,`task_name`,`completion_date`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `journal_entries`
--
ALTER TABLE `journal_entries`
  MODIFY `entry_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `mood_logs`
--
ALTER TABLE `mood_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=156;

--
-- AUTO_INCREMENT for table `task_completion`
--
ALTER TABLE `task_completion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `journal_entries`
--
ALTER TABLE `journal_entries`
  ADD CONSTRAINT `journal_entries_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`);

--
-- Constraints for table `mood_logs`
--
ALTER TABLE `mood_logs`
  ADD CONSTRAINT `mood_logs_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
