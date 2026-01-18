import React from 'react';

interface CardProps {
  children: React.ReactNode;
  className?: string;
  variant?: 'default' | 'bordered';
}

export function Card({ children, className = '', variant = 'default' }: CardProps) {
  const baseClasses = "bg-white rounded-xl p-6";
  
  const variantClasses = {
    default: "shadow-lg",
    bordered: "border-4 border-[#3498DB] shadow-xl"
  };

  return (
    <div className={`${baseClasses} ${variantClasses[variant]} ${className}`}>
      {children}
    </div>
  );
}
