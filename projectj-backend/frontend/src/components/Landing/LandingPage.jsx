import React from 'react';
import { Link } from 'react-router-dom';
import MyNavBar from './MyNavBar';
import heroImg from '../../assets/hero.png';
import './Landing.css';

const LandingPage = () => {
  return (
    <div className="landing-layout">
      <MyNavBar />
      <main className="main-content">
        {/* Hero Section */}
        <section className="hero-section">
          <div className="hero-text-container">
            <div className="hero-tag">🚀 Connecting Careers & Talent</div>
            <h1 className="hero-title">
              Find Your <span className="gradient-text">Perfect Match</span> in Tech
            </h1>
            <p className="hero-subtitle">
              HireSense bridges the gap between elite developers and top recruiters. Apply with custom resumes, track applications in real-time, and hire the best talent.
            </p>
            <div className="hero-buttons">
              <Link to="/register" className="btn-primary btn-large">Join as Candidate</Link>
              <Link to="/register?role=recruiter" className="btn-secondary btn-large">Post a Job</Link>
            </div>
            <div className="hero-stats">
              <div className="stat-item">
                <span className="stat-num">500+</span>
                <span className="stat-label">Active Jobs</span>
              </div>
              <div className="stat-divider"></div>
              <div className="stat-item">
                <span className="stat-num">10k+</span>
                <span className="stat-label">Candidates</span>
              </div>
              <div className="stat-divider"></div>
              <div className="stat-item">
                <span className="stat-num">98%</span>
                <span className="stat-label">Success Rate</span>
              </div>
            </div>
          </div>
          <div className="hero-image-container">
            <div className="hero-image-glow"></div>
            <img src={heroImg} alt="Talent Connection" className="hero-img" />
          </div>
        </section>

        {/* Features Section */}
        <section id="features" className="features-section">
          <h2 className="section-title">Why Choose <span className="gradient-text">HireSense</span>?</h2>
          <p className="section-subtitle">A streamlined platform designed for both candidates seeking jobs and recruiters seeking elite professionals.</p>
          
          <div className="features-grid">
            <div className="feature-card candidate-card">
              <div className="feature-icon">🎓</div>
              <h3>For Candidates</h3>
              <ul className="feature-list">
                <li>Create comprehensive professional profiles</li>
                <li>Secure resume storage with Cloudinary CDN</li>
                <li>Apply to matching jobs with a single click</li>
                <li>Real-time application status tracking</li>
              </ul>
            </div>

            <div className="feature-card recruiter-card">
              <div className="feature-icon">💼</div>
              <h3>For Recruiters</h3>
              <ul className="feature-list">
                <li>Post and manage target tech job listings</li>
                <li>Filter applicants based on skills and experience</li>
                <li>Manage application pipeline (Accept/Reject/Pending)</li>
                <li>Automated recruiter identification via secure JWT</li>
              </ul>
            </div>
          </div>
        </section>

        {/* Call to Action */}
        <section className="cta-section">
          <div className="cta-content">
            <h2>Ready to Elevate Your Career or Team?</h2>
            <p>Join thousands of professionals already using HireSense today.</p>
            <Link to="/register" className="btn-primary btn-large btn-cta">Create Your Free Account</Link>
          </div>
        </section>
      </main>

      <footer className="footer-section">
        <p>&copy; {new Date().getFullYear()} HireSense. Built with Spring Boot, React, and Spring AI.</p>
      </footer>
    </div>
  );
}

export default LandingPage;
