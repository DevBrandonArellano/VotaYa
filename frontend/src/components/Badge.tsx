import React from 'react';

interface BadgeProps {
  status: 'active' | 'pending' | 'completed' | 'validated';
  children: React.ReactNode;
}

export function Badge({ status, children }: BadgeProps) {
  const statusClasses = {
    active: 'bg-green-100 text-green-700 border-green-300',
    pending: 'bg-yellow-100 text-yellow-700 border-yellow-300',
    completed: 'bg-blue-100 text-blue-700 border-blue-300',
    validated: 'bg-green-100 text-green-800 border-green-400'
  };

  return (
    <span className={`px-3 py-1 rounded-full text-sm border-2 inline-flex items-center gap-1 ${statusClasses[status]}`}>
      {children}
    </span>
  );
}
